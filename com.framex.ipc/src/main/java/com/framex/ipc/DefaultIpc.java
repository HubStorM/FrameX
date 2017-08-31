package com.framex.ipc;

import com.framex.core.constant.*;
import com.framex.ipc.zookeeper.ZookeeperHelper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DefaultIpc implements IpcInterface{

    private static Map<String, SyncAbstractService> syncServices = new HashMap<String, SyncAbstractService>();

    private static Set<One2OneSyncService> initOne2OneSyncServicesCache = new HashSet<One2OneSyncService>();

    /**
     * zk服务节点同步
     */
    private static final class ServiceSyncWatcher implements Watcher{
        @Override
        public void process(WatchedEvent event) {

        }
    }

    /**
     *
     * @param service soa层传入的需要注册的服务
     * @param type soa层传入的服务类型，目前只处理一对一同步请求类型和一对多异步发布/订阅类型的服务
     */
    @Override
    public void ipcRegisterService(AbstractService service, ServiceType type){
        Optional.ofNullable(type).ifPresentOrElse(t -> {
            if(t.equals(ServiceType.ONE2ONE_SYNC)){
                registerOne2OneSyncService((One2OneSyncService)service);
            }
        }, () -> {throw new NullPointerException("ServiceType must not be null.");});
    }


    private void syncServiceInZk(){

    }

    /**
     * 创建zk服务节点目录。
     */
    private void createZkStructure(){
        ZooKeeper zk = ZookeeperHelper.getConnection("localhost:2181", 5000, null);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //TODO connectString
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, ZookeeperConstant.SERVICE_LOCK.toString());
        try {
            if(lock.acquire(1000, TimeUnit.MILLISECONDS)){
                ZookeeperHelper.create(zk, ZookeeperConstant.SERVICE_ROOT.toString(), "service_root_path".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if(zk.exists(ZookeeperConstant.SERVICE_ROOT.toString(), null) != null){
                //TODO 确定模块名称
                ZookeeperHelper.create(zk, "/service/module1", "module1".getBytes());
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void registerOne2OneSyncService(One2OneSyncService service){

    }

    private void registerOne2OneSyncService(ZooKeeper zk, One2OneSyncService service){

    }


    /**
     * 将服务加入缓存，使用commitToRegister一次注册所有服务。
     * @param service service to register
     */
    public void addToRegisterCache(AbstractService service){
        if(service.getSelfType().equals(ServiceType.ONE2ONE_SYNC))
            this.initOne2OneSyncServicesCache.add((One2OneSyncService)service);
    }

    /**
     * TODO 注册其他类型的服务
     */
    public void commitToRegister(){
        String connectString = "";
        int timeOut = 5000;
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        };
        ZooKeeper zk = ZookeeperHelper.getConnection(connectString, timeOut, watcher);
        initOne2OneSyncServicesCache.parallelStream().forEach(service -> {
            registerOne2OneSyncService(zk, service);
        });
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initOne2OneSyncServicesCache.clear();
    }

    public static Map<String, SyncAbstractService> getSyncServices() {
        return syncServices;
    }

    public static void setSyncServices(Map<String, SyncAbstractService> syncServices) {
        DefaultIpc.syncServices = syncServices;
    }
}
