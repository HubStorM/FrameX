package com.framex.persistence.dao.jdbc;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author lijie
 * @date 2017/10/25 20:50
 * @description
 */
public class BeanInfoHelper {
    private static final Map<Class<?>, SingletonBeanInfo> cache =  BeanInfoCache.INSTANCE.getCache();

    public static void analysisPackage(String packageName){
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(Entity.class);
        allClasses.forEach(BeanInfoHelper::analysisBean);
    }


    private static void analysisBean(Class<?> beanType){
        try {
            SingletonBeanInfo singletonBeanInfo = new SingletonBeanInfo();
            singletonBeanInfo.setBeanClass(beanType);
            singletonBeanInfo.setBeanName(beanType.getSimpleName());

            Map<String, ReadWritePair> map = new HashMap<>();
            Set<Method> readMethods = new HashSet<>();
            Set<Method> writeMethods = new HashSet<>();

            BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for(PropertyDescriptor item : pds){
                String propertyName = item.getName();
                Method readMethod = item.getReadMethod();
                Method writeMethod = item.getWriteMethod();
                map.put(propertyName, new ReadWritePair(readMethod, writeMethod));
                readMethods.add(readMethod);
                writeMethods.add(writeMethod);
                if(readMethod.isAnnotationPresent(Id.class)){
                    singletonBeanInfo.setPrimaryKeyName(propertyName);
                }
            }
            singletonBeanInfo.setReadWritePairMap(map);
            singletonBeanInfo.setReadMethods(readMethods);
            singletonBeanInfo.setWriteMethods(writeMethods);

            Table table = beanType.getDeclaredAnnotation(Table.class);
            if(table == null){
                singletonBeanInfo.setTableName(beanType.getSimpleName().toLowerCase());
            }
            else{
                singletonBeanInfo.setTableName(table.name());
            }

            cache.put(beanType, singletonBeanInfo);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public static Set<Method> getReadMethods(Class<?> beanType){
        return cache.get(beanType).getReadMethods();
    }

    public static Set<Method> getWriteMethods(Class<?> beanType){
        return cache.get(beanType).getWriteMethods();
    }

    public static Map<String, ReadWritePair> getReadWritePairMap(Class<?> beanType){
        return cache.get(beanType).getReadWritePairMap();
    }

    public static String getPrimaryKey(Class<?> beanType){
        return cache.get(beanType).getPrimaryKeyName();
    }

    public static String getBeanName(Class<?> beanType){
        return cache.get(beanType).getBeanName();
    }

    public static String getTableName(Class<?> beanType){
        return cache.get(beanType).getTableName();
    }

    public static SingletonBeanInfo getSingletonBeanInfo(Class<?> beanType){
        return cache.get(beanType);
    }


}
