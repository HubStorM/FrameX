package com.framex.persistence.dao;

import com.framex.persistence.SpringContextUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/21 12:46
 * @description
 */
public enum SingletonDao implements Dao{
    INSTANCE;

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private SingletonDao(){
        dataSource = SpringContextUtil.getApplicationContext().getBean("defaultDataSource", DataSource.class);
        jdbcTemplate = SpringContextUtil.getApplicationContext().getBean("defaultJdbcTemplate", JdbcTemplate.class);
    }

    @Override
    public DaoTypeEnum getType() {
        return DaoTypeEnum.SINGLETON;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean supportDynamicDataSource() {
        return false;
    }

    @Override
    public Dao changeDataSource(String dataSourceBeanName) {
        throw new RuntimeException("singleton dao does not support dynamicDataSource");
    }


    @Override
    public <T> T findObject(String sql, Class<T> requiredType, Object... args) {
        return jdbcTemplate.queryForObject(sql, requiredType, args);
    }

    @Override
    public boolean supportJdbcTemplate() {
        return true;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        if(supportJdbcTemplate())
            return jdbcTemplate;
        throw new NullPointerException("");
    }


    @Override
    public <T> T findObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
        return jdbcTemplate.queryForObject(sql, args, argTypes, requiredType);
    }

    @Override
    public Map<String, Object> findObject(String sql, Object... args) {
        return jdbcTemplate.queryForMap(sql, args);
    }

    @Override
    public Map<String, Object> findObject(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForMap(sql, args, argTypes);
    }

    @Override
    public <T> List<T> findList(String sql, Class<T> requiredType, Object... args) {
        return jdbcTemplate.queryForList(sql, requiredType, args);
    }

    @Override
    public <T> List<T> findList(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
        return jdbcTemplate.queryForList(sql, args, argTypes, requiredType);
    }

    @Override
    public List<Map<String, Object>> findList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    @Override
    public List<Map<String, Object>> findList(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForList(sql, args, argTypes);
    }

    @Override
    public int update(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int update(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public <T> void update(T item) {

    }


}
