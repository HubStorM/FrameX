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
    public Class<?> beanClass;
    public String beanName;
    public Set<Method> readMethods;
    public Set<Method> writeMethods;
    public Map<String, ReadWritePair> readWritePairMap;
}
