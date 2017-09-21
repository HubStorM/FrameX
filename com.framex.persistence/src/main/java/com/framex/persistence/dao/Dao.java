package com.framex.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author lijie
 * @date 2017/9/21 12:45
 * @description dao接口，模块的单例dao或者是之后要增加的多实例的dao都继承此接口。
 */
public interface Dao {
    Dao getInstance(DaoTypeEnum type);
    DaoTypeEnum getType();
    DataSource getDataSource();

    <T> T findObject(String sql, Class<T> requiredType, Object... sqlArgs);
    <T> List<T> findList(String sql, Class<T> requiredType, Object... sqlArgs);
    <T> List<T> findList(String sql, Object[] sqlArgs, int[] argTypes, Class<T> requiredType);
    int update(String sql, Object... sqlArgs);
    int update(String sql, Object[] sqlArgs, int[] argTypes);

    boolean supportJdbcTemplate();
    JdbcTemplate getJdbcTemplate();
}
