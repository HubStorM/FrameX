package com.framex.web.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 
 * spring application辅助类。目前主要用于获取根applicationContext
 * @作者 undefined
 * @version [版本号, 2017年7月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ApplicationContextUtil implements ApplicationContextAware{

    @Autowired
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getContext(){
        return applicationContext;
    }

}
