package com.framex.persistence.datasource.dynamic;

public class DynamicDataSource extends AbstractDynamicDataSource {

    private String dataSourceHolder;

    @Override
    protected Object determineCurrentDataSourceName() {
        return this.dataSourceHolder;
    }

    public String getDataSourceHolder() {
        return dataSourceHolder;
    }

    public DynamicDataSource setDataSourceHolder(String dataSourceHolder) {
        this.dataSourceHolder = dataSourceHolder;
        return this;
    }
}
