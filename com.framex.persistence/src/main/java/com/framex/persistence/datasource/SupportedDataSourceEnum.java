package com.framex.persistence.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijie
 * @date 2017/9/8 8:59
 * @description
 */
public enum SupportedDataSourceEnum {
    BASICDATASOURCE("BASICDATASOURCE",
            BasicDataSource.class,
            Arrays.asList("driverClassName", "url", "username", "password"),
            Arrays.asList()),
    COMBOPOOLEDDATASOURCE("COMBOPOOLEDDATASOURCE",
            ComboPooledDataSource.class,
            Arrays.asList("driverClass", "jdbcUrl", "user", "password"),
            Arrays.asList()
            );

    private String name;
    private Class<? extends DataSource> dataSourceClass;
    private List<String> necessary;
    private List<String> optional;

    private SupportedDataSourceEnum(String name, Class<? extends DataSource> dataSourceClass, List<String> necessary, List<String> optional){
        this.name = name;
        this.dataSourceClass = dataSourceClass;
        this.necessary = necessary;
        this.optional = optional;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends DataSource> getDataSourceClass() {
        return dataSourceClass;
    }

    public void setDataSourceClass(Class<? extends DataSource> dataSourceClass) {
        this.dataSourceClass = dataSourceClass;
    }

    public List<String> getNecessary() {
        return necessary;
    }

    public void setNecessary(List<String> necessary) {
        this.necessary = necessary;
    }

    public List<String> getOptional() {
        return optional;
    }

    public void setOptional(List<String> optional) {
        this.optional = optional;
    }
}
