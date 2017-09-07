package com.framex.persistence;

public class DynamicDataSource extends AbstractDynamicDataSource {
    @Override
    protected Object determineCurrentDataSourceName() {
        return DataSourceHolder.getDataSourceName();
    }
}
