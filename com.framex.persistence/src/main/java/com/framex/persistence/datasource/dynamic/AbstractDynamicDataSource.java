package com.framex.persistence.datasource.dynamic;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author lijie
 *
 */
public abstract class AbstractDynamicDataSource implements DataSource {

    private static final Map<Object, DataSource> dataSourcePool = new HashMap();

    public AbstractDynamicDataSource addDataSource(Object name, DataSource value){
        dataSourcePool.put(name, value);
        return this;
    }

    public AbstractDynamicDataSource removeDataSource(Object name){
        dataSourcePool.remove(name);
        return this;
    }

    public static void clearDataSourcePool(){
        dataSourcePool.clear();
    }

    protected DataSource determineTargetDataSource(){
        if(dataSourcePool.size() == 0)
            throw new IllegalStateException("No dataSource in dynamicDataSource found");
        Object currentDataSourceName = determineCurrentDataSourceName();
        DataSource currentDataSource = dataSourcePool.get(currentDataSourceName);
        if(currentDataSource == null)
            throw new NullPointerException("No such dataSource bean with name " + currentDataSourceName);
        return currentDataSource;
    }

    protected abstract Object determineCurrentDataSourceName();

    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("getLogWriter");
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("setLogWriter");
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("setLoginTimeout");
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return determineTargetDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this);
    }
}
