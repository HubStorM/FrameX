package com.framex.persistence.dao.jdbc;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @date 2017/10/25 21:11
 * @description
 */
public class SingletonBeanInfo {
    private Class<?> beanClass;
    private String beanName;
    private Set<Method> readMethods;
    private Set<Method> writeMethods;
    private Map<String, ReadWritePair> readWritePairMap;
    private String primaryKeyName;

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Set<Method> getReadMethods() {
        return readMethods;
    }

    public void setReadMethods(Set<Method> readMethods) {
        this.readMethods = readMethods;
    }

    public Set<Method> getWriteMethods() {
        return writeMethods;
    }

    public void setWriteMethods(Set<Method> writeMethods) {
        this.writeMethods = writeMethods;
    }

    public Map<String, ReadWritePair> getReadWritePairMap() {
        return readWritePairMap;
    }

    public void setReadWritePairMap(Map<String, ReadWritePair> readWritePairMap) {
        this.readWritePairMap = readWritePairMap;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }


    @Override
    public String toString() {
        return "SingletonBeanInfo{" +
                "beanClass=" + beanClass +
                ", beanName='" + beanName + '\'' +
                ", readMethods=" + readMethods +
                ", writeMethods=" + writeMethods +
                ", readWritePairMap=" + readWritePairMap +
                ", primaryKeyName='" + primaryKeyName + '\'' +
                '}';
    }
}
