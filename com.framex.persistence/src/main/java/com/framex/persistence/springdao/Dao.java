package com.framex.persistence.springdao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author lijie
 * @date 2017/9/15 13:41
 * @description
 */
public interface Dao {
    boolean supportJdbcTemplate();
    JdbcTemplate getJdbcTemplate();
    boolean supportChangeDataSource();
    boolean isShared();
    void setIsShared(boolean isShared);
    DataSource changeDataSource(String name);
    DataSource changeDataSource(DataSource name);
}
