package com.framex.persistence;

import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.List;

public interface PersistenceInterface {

    void registerDataSource(DataSource dataSource, String beanName);
    void registerTransactionManager(PlatformTransactionManager transactionManager, String dataSourceName, String beanName);
    List<DataSource> getDataSources();
    List<PlatformTransactionManager> getTransactionManagers();
    DataSource getDataSource(String beanName);
    PlatformTransactionManager getTransactionManager(String beanName);
    void flush();
}
