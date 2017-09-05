package com.framex.persistence;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ImportResource("classpath:spring-persistence.xml")
public class PersistentConfig {
    @Bean("springContextUtil")
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }
}
