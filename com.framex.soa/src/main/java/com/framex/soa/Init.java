package com.framex.soa;

import com.framex.core.constant.ZookeeperConstant;
import com.framex.persistence.framexconfig.Configuration;
import com.framex.persistence.framexconfig.ConfigurationHolder;
import com.framex.soa.zookeeper.CuratorHelper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lijie
 * @date 2017/9/24 23:04
 * @description 框架入口
 */
public abstract class Init {

    public static final Logger log = LoggerFactory.getLogger(Init.class);

    private static CuratorFramework client;

    private static ServiceCenter serviceCenter;

    public boolean init(String classPathFileName){
        ClassPathResource configurationResource = new ClassPathResource(classPathFileName);
        try(InputStream input = configurationResource.getInputStream()){
            Yaml yaml = new Yaml();
            Configuration config = yaml.loadAs(input, Configuration.class);
            ConfigurationHolder.setConfiguration(config);
            checkFrameConfiguration();
        } catch (IOException e) {
            log.debug("Parsing framex configuration file error.");
            e.printStackTrace();
            return false;
        }

        client = CuratorHelper.getZkFromFrameConfiguration();
        client.start();

        try {
            initServiceCenterRootNode(client);
            initModuleRootNode(client);
            initDefaultGroupNode(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceCenter = new ServiceCenter(client);

        return true;
    }


    abstract boolean customProcedure();

    private void initServiceCenterRootNode(CuratorFramework client) throws Exception{
        Map<String, String> zookeeper = ConfigurationHolder.getConfiguration().getZookeeper();
        String serviceRoot = zookeeper.get("serviceRoot");
        InterProcessMutex lock = new InterProcessMutex(client, ZookeeperConstant.SERVICE_LOCK.toString());
        if(lock.acquire(1000 * 5, TimeUnit.MILLISECONDS)){
            if(client.checkExists().forPath(serviceRoot) == null){
                client.create().forPath(serviceRoot);
            }
            lock.release();
        }
        else{
            while(client.checkExists().forPath(serviceRoot) == null){
                log.debug("Waiting for creating service root node.");
                Thread.sleep(200);
            }
        }
    }

    private void initModuleRootNode(CuratorFramework client) throws Exception {
        Map<String, String> zookeeper = ConfigurationHolder.getConfiguration().getZookeeper();
        String serviceRoot = zookeeper.get("serviceRoot");
        String modulePath = serviceRoot + ConfigurationHolder.getConfiguration().getModule().getModuleUri();
        if(client.checkExists().forPath(modulePath) != null){
            client.delete().deletingChildrenIfNeeded().forPath(modulePath);
        }
        client.create().forPath(modulePath);
    }

    private void initDefaultGroupNode(CuratorFramework client) throws Exception {
        Map<String, String> zookeeper = ConfigurationHolder.getConfiguration().getZookeeper();
        String serviceRoot = zookeeper.get("serviceRoot");
        String moduleUri = ConfigurationHolder.getConfiguration().getModule().getModuleUri();
        String defaultGroupNode = zookeeper.get("defaultGroupNode");
        if(client.checkExists().forPath(serviceRoot + moduleUri + defaultGroupNode) != null){
            client.delete().deletingChildrenIfNeeded().forPath(serviceRoot + moduleUri + defaultGroupNode);
        }
        client.create().forPath(serviceRoot + moduleUri + defaultGroupNode);
    }


    /**
     * TODO
     * 验证应用系统的配置文件
     * @return
     */
    private boolean checkFrameConfiguration(){
        return true;
    }

}
