package com.framex.persistence.dao.jdbc;

import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @date 2017/10/25 20:50
 * @description
 */
public class BeanInfoHelper {
    private static final Map<Class<?>, SingletonBeanInfo> cache =  BeanInfoCache.INSTANCE.getCache();

    public static void analysisPackage(String packageName){
        Reflections reflections = new Reflections("my.project.prefix");
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        allClasses.forEach(BeanInfoHelper::analysisBean);
    }


    private static void analysisBean(Class<?> beanType){

    }

    public static Set<Method> getReadMethods(Class<?> beanType){
        return cache.get(beanType).readMethods;
    }

    public static Set<Method> getWriteMethods(Class<?> beanType){
        return cache.get(beanType).writeMethods;
    }

    public static Map<String, ReadWritePair> getReadWritePairMap(Class<?> beanType){
        return cache.get(beanType).readWritePairMap;
    }


}
