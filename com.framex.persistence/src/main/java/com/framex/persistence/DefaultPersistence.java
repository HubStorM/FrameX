package com.framex.persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

public class DefaultPersistence implements PersistenceInterface{



    @Override
    public void registerDataSource(DataSource dataSourceDefinition) {

    }

    @Override
    public void registerTransactionManager(PlatformTransactionManager transactionManagerDefinition) {

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

    public static void main(String... args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistentConfig.class);
        System.out.println(context.getBean("springContextUtil"));
    }
}
