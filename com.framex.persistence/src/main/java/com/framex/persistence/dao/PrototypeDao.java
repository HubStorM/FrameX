package com.framex.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/21 23:11
 * @description
 */
public class PrototypeDao implements Dao{

    @Override
    public DaoTypeEnum getType() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }

    @Override
    public boolean supportJdbcTemplate() {
        return false;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return null;
    }

    @Override
    public <T> T findObject(String sql, Class<T> requiredType, Object... args) {
        return null;
    }

    @Override
    public <T> T findObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
        return null;
    }

    @Override
    public Map<String, Object> findObject(String sql, Object... args) {
        return null;
    }

    @Override
    public Map<String, Object> findObject(String sql, Object[] args, int[] argTypes) {
        return null;
    }

    @Override
    public <T> List<T> findList(String sql, Class<T> requiredType, Object... args) {
        return null;
    }

    @Override
    public <T> List<T> findList(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findList(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findList(String sql, Object[] args, int[] argTypes) {
        return null;
    }

    @Override
    public int update(String sql, Object... args) {
        return 0;
    }

    @Override
    public int update(String sql, Object[] args, int[] argTypes) {
        return 0;
    }
}
