package com.framex.soa.service;

import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.apache.curator.framework.CuratorFramework;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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
        for(Class<?> serviceClass : allClasses){
            Service serviceGroup = serviceClass.getDeclaredAnnotation(Service.class);
            String group = serviceGroup.group();
            String groupVersion = serviceGroup.version();
            Method[] methods = serviceClass.getDeclaredMethods();
            for(Method method : methods){
                Annotation[] annotations = method.getDeclaredAnnotations();
                if(annotations != null){
                    for (Annotation annotation : annotations) {
                        if(annotation.annotationType().equals(One2OneAsyncService.class)){

                        }
                    }
                }

            }

        }

    }






}
