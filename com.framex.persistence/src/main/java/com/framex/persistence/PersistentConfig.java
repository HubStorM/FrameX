package com.framex.persistence;

import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@Configurable
public class PersistentConfig {
    @Bean("springContextUtil")
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource instance = new DynamicDataSource();
        instance.setTargetDataSources(new HashMap<Object, Object>());
        return instance;
    }
}
