package com.framex.ipc.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperHelper {

    public static ZooKeeper getConnection(String connectString, int timeOut, Watcher watcher){
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper(connectString, timeOut, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
                System.out.println("Got an event on zookeeper : "
                        + event.getState() + "-" + event.getType() + " on " + event.getPath());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connectedSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zoo;
    }

    public static boolean create(ZooKeeper zk, String path, byte[] data){
        try {
            zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String... args) throws Exception{
        ZooKeeper zoo = getConnection("127.0.0.1:2181", 5000, null);
        System.out.println(zoo);
        /*try {
            System.out.println(zoo.exists("/zookeeper", null));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            zoo.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        try {
            zoo.exists("/test", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //zoo.getChildren("/test", true);
        zoo.getData("/test/test2", true, null);
        //create(zoo, "/test/test4", "testdata".getBytes());
        //System.out.println(create(zoo, "/test/test3", "testdata".getBytes()));
        zoo.delete("/test/test2", -1);
        try {
            zoo.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
