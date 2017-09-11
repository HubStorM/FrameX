package com.framex.persistence.datasource;

import com.framex.persistence.DefaultPersistence;
import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author lijie
 * @date 2017/9/8 8:50
 * @description
 */
public class DataSourceFactory {
    public static DataSource buildDataSource(String beanName, SupportedDataSourceEnum dataSourceType, List<Object> necessary, Map<String, Object> optional){
        if(dataSourceType.getNecessary().size() != 0 && necessary == null) {
            throw new NullPointerException("Necessary args for " + dataSourceType.getDataSourceClass() + " is needed");
        }
        if(!Objects.equals(dataSourceType.getNecessary().size(), necessary.size())) {
            throw new IllegalArgumentException("Necessary args error.Need "
                    + dataSourceType.getNecessary() + ",But get " + Arrays.asList(necessary));
        }
        DataSource dataSource = builder(dataSourceType, necessary, optional);
        new DefaultPersistence().registerDataSource(dataSource, beanName);
        new DynamicDataSource().addDataSource(beanName, SpringContextUtil.getApplicationContext().getBean(beanName, DataSource.class));
        return dataSource;
    }

    private static DataSource builder(SupportedDataSourceEnum dataSourceType, List<Object> necessary, Map<String, Object> optional) {
        Class<? extends DataSource> type = dataSourceType.getDataSourceClass();
        DataSource dataSource;
        try {
            dataSource = type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Create instance of " + type + " error");
        }
        //inject necessary args
        final List<String> necessarySuffix = dataSourceType.getNecessary();
        IntStream.range(0, necessarySuffix.size()).forEach(i -> {
            String name = necessarySuffix.get(i);
            Object value = necessary.get(i);
            try {
                Method setMethod = type.getDeclaredMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1),
                        value.getClass());
                setMethod.invoke(dataSource, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //inject optional args
        final List<String> optionalSuffix = dataSourceType.getOptional();
        Optional.ofNullable(optionalSuffix).ifPresent(t -> t.forEach(suffix -> {
            Object value = optional.get(suffix);
            try {
                Method setMethod = type.getDeclaredMethod("set" + suffix.substring(0, 1).toUpperCase() + suffix.substring(1),
                        value.getClass());
                setMethod.invoke(dataSource, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));


        return dataSource;

    }
}
