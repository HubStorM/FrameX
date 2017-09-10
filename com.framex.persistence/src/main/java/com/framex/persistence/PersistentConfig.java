package com.framex.persistence;

import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;

@Configurable
@ComponentScan("com.framex")
@ImportResource("spring-persistence.xml")
public class PersistentConfig {
    @Bean("springContextUtil")
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        return new DynamicDataSource();
    }



}
