package com.framex.persistence.dao.jdbc;

import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.persistence.Id;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
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
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(false)));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
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

            BeanInfo beanInfo = Introspector.getBeanInfo(beanType);
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


}
