package com.framex.persistence;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;
import java.util.Map;

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
