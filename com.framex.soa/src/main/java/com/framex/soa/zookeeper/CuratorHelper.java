package com.framex.soa.zookeeper;

import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Map;

/**
 * @author lijie
 * @date 2017/10/2 22:15
 * @description
 */
public class CuratorHelper {
    public static CuratorFramework getZkFromFrameConfiguration(){
        Map<String, String> zookeeper = ConfigurationHolder.getConfiguration().getZookeeper();
        String ip = zookeeper.get("ip");
        String port = zookeeper.get("port");
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.builder()
                .connectString(ip + ":" + port)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(5000)
                .build();
    }
}
