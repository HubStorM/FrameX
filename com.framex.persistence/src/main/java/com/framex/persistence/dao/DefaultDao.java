package com.framex.persistence.dao;

import com.framex.persistence.SpringContextUtil;

import javax.sql.DataSource;

/**
 * @author lijie
 * @date 2017/9/21 12:46
 * @description
 */
public enum DefaultDao implements Dao{
    INSTANCE;

    private final DataSource dataSource;

    private DefaultDao(){
        dataSource = SpringContextUtil.getApplicationContext().getBean("defaultDataSource", DataSource.class);
    }

    @Override
    public Dao getInstance(DaoTypeEnum type) {
        return INSTANCE;
    }
    @Override
    public DaoTypeEnum getType() {
        return DaoTypeEnum.SINGLETON;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

}
