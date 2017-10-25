package com.framex.persistence.dao.jdbc;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/10/25 20:51
 * @description
 */
public enum BeanInfoCache {
    INSTANCE;
    private static final Map<Class<?>, SingletonBeanInfo> cache = new HashMap<>();

    public Map<Class<?>, SingletonBeanInfo> getCache() {
        return cache;
    }
}
