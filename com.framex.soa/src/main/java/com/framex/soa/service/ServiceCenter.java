package com.framex.soa.service;

import com.framex.persistence.framexconfig.ConfigurationHolder;
import com.framex.persistence.framexconfig.FramexModule;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServiceCenter {

    private static final Logger log = LoggerFactory.getLogger(ServiceCenter.class);

    private static final ConcurrentHashMap<ServiceKey, Object> serviceMap = new ConcurrentHashMap<ServiceKey, Object>();

    private static final ExecutorService taskPool = Executors.newFixedThreadPool(50);

    private static AtomicBoolean run = new AtomicBoolean(false);

    private CuratorFramework client;

    public ServiceCenter(CuratorFramework client) {
        this.client = client;
    }

    private Map<String, String> zookeeper = ConfigurationHolder.getConfiguration().getZookeeper();

    public void scanLocalService() throws Exception {
        String servicePackageName = ConfigurationHolder.getConfiguration().getModule().getServicePackageName();
        Reflections reflections = new Reflections(servicePackageName + ".implement");
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Service.class);
        for (Class<?> serviceClass : allClasses) {
            Service service = serviceClass.getDeclaredAnnotation(Service.class);
            if (service != null) {
                String name = service.name();
                Class<?> target = service.target();
                String version = service.version();

                String serviceNode = zookeeper.get("serviceRoot") + ConfigurationHolder.getConfiguration().getModule().getModuleUri() + "/" + name;
                FramexModule module = ConfigurationHolder.getConfiguration().getModule();
                String serviceNodeData = "moduleIp=" + module.getModuleIp()
                        + ";modulePort=" + module.getModulePort()
                        + ";moduleUri=" + module.getModuleUri()
                        + ";rpcPort=" + module.getRpcPort();

                InterProcessMutex lock = new InterProcessMutex(client, serviceNode);
                if (lock.acquire(1000 * 5, TimeUnit.MILLISECONDS)) {
                    if (client.checkExists().forPath(serviceNode) == null) {
                        client.create().forPath(serviceNode, serviceNodeData.getBytes());
                    }
                    lock.release();
                } else {
                    while (client.checkExists().forPath(serviceNode) == null) {
                        log.debug("Waiting for creating service node : " + serviceNode);
                        Thread.sleep(200);
                    }
                }

                String serviceAndVersion = serviceNode + "/" + version;
                if (client.checkExists().forPath(serviceAndVersion) != null) {
                    client.delete().deletingChildrenIfNeeded().forPath(serviceAndVersion);
                }
                client.create().forPath(serviceAndVersion, target.getSimpleName().getBytes());
                registerService(name, version, target, serviceClass.newInstance());
            }
        }
        startLocalServiceServer(Integer.parseInt(ConfigurationHolder.getConfiguration().getModule().getRpcPort()));
    }


    public <T> T findService(String serviceName, String serviceVersion, Class<T> targetInterface) {
        String serviceNode = zookeeper.get("serviceRoot") + ConfigurationHolder.getConfiguration().getModule().getModuleUri() + "/" + serviceName;
        String nodeData = null;
        try {
            if (client.checkExists().forPath(serviceNode) != null) {
                byte[] bytes = client.getData().forPath(serviceNode);
                if (bytes != null) {
                    nodeData = new String(bytes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(nodeData)) {
            String host = null;
            int port = 0;
            String[] nodeDataArrays = nodeData.split(";");
            for (String nodeItem : nodeDataArrays) {
                String[] nodeKeyAndValue = nodeItem.split("=");
                if ("moduleIp".equals(nodeKeyAndValue[0])) {
                    host = nodeKeyAndValue[1];
                }
                if ("rpcPort".equals(nodeKeyAndValue[0])) {
                    port = Integer.valueOf(nodeKeyAndValue[1]);
                }
            }
            ;
            Socket socket = null;
            InputStream is = null;
            OutputStream os = null;
            ObjectInput oi = null;
            ObjectOutput oo = null;
            try {
                socket = new Socket(host, port);
                os = socket.getOutputStream();
                oo = new ObjectOutputStream(os);
                oo.writeUTF(serviceName);
                oo.writeUTF(serviceVersion);
                oo.writeObject(targetInterface);

                is = socket.getInputStream();
                oi = new ObjectInputStream(is);
                return (T) oi.readObject();
            } catch (Exception e) {
                return null;
            } finally {

                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (oi != null) {
                        oi.close();
                    }
                    if (oo != null) {
                        oo.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    private void registerService(String name, String version, Class<?> targetInterface, Object targetInstance) {
        serviceMap.put(new ServiceKey(name, version, targetInterface), targetInstance);
    }

    private class ServiceKey {
        private String name;
        private String version;
        private Class<?> target;

        ServiceKey(String name, String version, Class<?> target) {
            this.name = name;
            this.version = version;
            this.target = target;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Class<?> getTarget() {
            return target;
        }

        public void setTarget(Class<?> target) {
            this.target = target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ServiceKey that = (ServiceKey) o;

            return name.equals(that.name) && version.equals(that.version) && target.equals(that.target);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + version.hashCode();
            result = 31 * result + target.hashCode();
            return result;
        }
    }

    private void startLocalServiceServer(final int port) {
        Runnable lifeThread = new Runnable() {
            @Override
            public void run() {
                ServerSocket lifeSocket = null;
                Socket client = null;
                ServiceTask serviceTask = null;
                try {
                    lifeSocket = new ServerSocket(port);
                    run.set(true);
                    while (run.get()) {
                        client = lifeSocket.accept();
                        serviceTask = new ServiceTask(client);
                        serviceTask.accept();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        taskPool.execute(lifeThread);
    }

    private final class ServiceTask implements Runnable {

        private Socket client;

        ServiceTask(Socket client) {
            this.client = client;
        }

        void accept() {
            taskPool.execute(this);
        }

        @Override
        public void run() {
            InputStream is = null;
            ObjectInput oi = null;
            OutputStream os = null;
            ObjectOutput oo = null;
            try {
                is = client.getInputStream();
                os = client.getOutputStream();
                oi = new ObjectInputStream(is);
                String serviceName = oi.readUTF();
                String serviceVersion = oi.readUTF();
                Class<?> target = (Class<?>) oi.readObject();
                Object targetService = serviceMap.get(new ServiceKey(serviceName, serviceVersion, target));
                oo = new ObjectOutputStream(os);
                oo.writeObject(targetService);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oo != null) {
                        oo.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                    if (oi != null) {
                        oi.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


