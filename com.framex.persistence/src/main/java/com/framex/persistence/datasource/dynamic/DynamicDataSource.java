package com.framex.persistence.datasource.dynamic;

public class DynamicDataSource extends AbstractDynamicDataSource {
    @Override
    protected Object determineCurrentDataSourceName() {
        return DynamicDataSourceHolder.getDataSourceName();
    }
}
