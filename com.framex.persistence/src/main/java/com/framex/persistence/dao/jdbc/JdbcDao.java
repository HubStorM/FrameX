package com.framex.persistence.dao.jdbc;

import com.framex.persistence.dao.DaoTypeEnum;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/21 12:45
 * @description dao接口，模块的单例dao或者是之后要增加的多实例的dao都继承此接口。
 */
public interface JdbcDao {
    DaoTypeEnum getType();
    DataSource getDataSource();
    boolean supportDynamicDataSource();
    JdbcDao changeDataSource(String dataSourceBeanName);
    boolean supportJdbcTemplate();
    JdbcTemplate getJdbcTemplate();

    <T> T findObject(String sql, Class<T> requiredType, Object... args);
    <T> T findObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType);
    Map<String, Object> findObject(String sql, Object... args);
    Map<String, Object> findObject(String sql, Object[] args, int[] argTypes);

    <T> List<T> findList(String sql, Class<T> requiredType, Object... args);
    <T> List<T> findList(String sql, Object[] args, int[] argTypes, Class<T> requiredType);
    List<Map<String, Object>> findList(String sql, Object... args);
    List<Map<String, Object>> findList(String sql, Object[] args, int[] argTypes);

    int execute(String sql, Object... args);
    int execute(String sql, Object[] args, int[] argTypes);


}
