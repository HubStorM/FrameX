package com.framex.soa.service;

import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.apache.curator.framework.CuratorFramework;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;

public class ServiceCenter{

    private static CuratorFramework client;

    public ServiceCenter(CuratorFramework client) {
        this.client = client;
    }

    private void scanLocalService(){
        String servicePackageName = ConfigurationHolder.getConfiguration().getModule().getServicePackageName();
        Reflections reflections = new Reflections(servicePackageName + ".api");
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Service.class);
        for(Class<?> item : allClasses){
        }

    }






}
