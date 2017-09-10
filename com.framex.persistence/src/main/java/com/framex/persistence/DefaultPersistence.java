package com.framex.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

public class DefaultPersistence implements PersistenceInterface{
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(PersistentConfig.class);

    public DefaultPersistence(){
    }

    @Override
    public void registerDataSource(DataSource dataSource, String beanName) {
        /*
        Class<?> dataSourceClass = dataSource.getClass();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(dataSourceClass);
        if(dataSourceClass == BasicDataSource.class){
            builder.addPropertyValue("driverClassName", ((BasicDataSource) dataSource).getDriverClassName())
                    .addPropertyValue("url", ((BasicDataSource) dataSource).getUrl())
                    .addPropertyValue("username", ((BasicDataSource) dataSource).getUsername())
                    .addPropertyValue("password", ((BasicDataSource) dataSource).getPassword());
        }
        beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
        */
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();
        beanFactory.registerSingleton(beanName, dataSource);
    }

    @Override
    public void registerTransactionManager(PlatformTransactionManager transactionManager, String dataSourceName, String beanName) {
        if(context.getBean(dataSourceName) == null){
            throw new NoSuchBeanDefinitionException("No dataSource defined with name " + dataSourceName);
        }
        Class<?> txClass = transactionManager.getClass();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(txClass);
        if(txClass == DataSourceTransactionManager.class){
            builder.addPropertyReference("dataSource", dataSourceName);
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    @Override
    public List<DataSource> getDataSources() {
        return null;
    }

    @Override
    public List<PlatformTransactionManager> getTransactionManagers() {
        return null;
    }

    @Override
    public DataSource getDataSource(String beanName) {
        return null;
    }

    @Override
    public PlatformTransactionManager getTransactionManager(String beanName) {
        return null;
    }

    @Override
    public void flush() {

    }

    public static ApplicationContext getContext() {
        return context;
    }


}
