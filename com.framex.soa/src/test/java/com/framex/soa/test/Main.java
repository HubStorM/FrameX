package com.framex.soa.test;

import com.framex.soa.kong.KongHelper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

/**
 * @author lijie
 * @date 2017/10/2 18:42
 * @description
 */
public class Main {
    public static void main(String... args) {
        Reflections reflections = new Reflections("com.framex.soa.kong", new SubTypesScanner(false));
        System.out.println(reflections.getSubTypesOf(Object.class));
    }
}
