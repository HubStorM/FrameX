package com.framex.persistence.datasource.dynamic;

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> dataSourceName = new ThreadLocal<String>();

    public static String getDataSourceName(){
        return dataSourceName.get();
    }

    public static void setDataSourceName(String name){
        dataSourceName.set(name);
    }
}
