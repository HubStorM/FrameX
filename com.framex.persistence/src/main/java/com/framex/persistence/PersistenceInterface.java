package com.framex.persistence;

import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.List;

public interface PersistenceInterface {

    void registerDataSource(DataSource dataSourceDefinition);
    void registerTransactionManager(PlatformTransactionManager transactionManagerDefinition);
    List<DataSource> getDataSources();
    List<PlatformTransactionManager> getTransactionManagers();
    DataSource getDataSource(String beanName);
    PlatformTransactionManager getTransactionManager(String beanName);
    void flush();
}
