package com.framex.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

public class DefaultPersistence implements PersistenceInterface{
    private static ApplicationContext context;

    public DefaultPersistence(){
        context = new AnnotationConfigApplicationContext(PersistentConfig.class);
    }

    @Override
    public void registerDataSource(DataSource dataSource) {
        Class<?> dataSourceClass = dataSource.getClass();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(dataSourceClass);
        if(dataSourceClass == BasicDataSource.class){
            builder.addPropertyValue("driverClassName", ((BasicDataSource) dataSource).getDriverClassName())
                    .addPropertyValue("url", ((BasicDataSource) dataSource).getUrl())
                    .addPropertyValue("username", ((BasicDataSource) dataSource).getUsername())
                    .addPropertyValue("password", ((BasicDataSource) dataSource).getPassword());
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();
        //还有一种注册单例的方式，直接将dataSource作为参数。
        beanFactory.registerBeanDefinition("dataSource", builder.getBeanDefinition());
    }

    @Override
    public void registerTransactionManager(PlatformTransactionManager transactionManager) {
        Class<?> txClass = transactionManager.getClass();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(txClass);
        if(txClass == DataSourceTransactionManager.class){
        }
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

    public static void main(String... args) throws Exception{
        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistentConfig.class);
        System.out.println(context.getBean("springContextUtil"));*/

        /*DefaultPersistence persistence = new DefaultPersistence();
        persistence.registerDataSource(new BasicDataSource());*/

        /*DefaultPersistence persistence = new DefaultPersistence();
        persistence.registerDataSource(new BasicDataSource());
        System.out.println(context.getBean("dataSource"));*/

        /*DefaultPersistence persistence = new DefaultPersistence();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/oa");
        dataSource.setUsername("root");
        dataSource.setPassword("11111");
        persistence.registerDataSource(dataSource);
        System.out.println(context.getBean("dataSource", BasicDataSource.class).getConnection());*/

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/oa");
        dataSource.setUsername("root");
        dataSource.setPassword("11111");
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSource);
    }
}
