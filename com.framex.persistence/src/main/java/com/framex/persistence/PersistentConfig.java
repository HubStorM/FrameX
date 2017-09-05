package com.framex.persistence;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class PersistentConfig {
    @Bean("springContextUtil")
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }
}
