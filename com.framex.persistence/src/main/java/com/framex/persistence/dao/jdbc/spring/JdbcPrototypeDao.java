package com.framex.persistence.dao.jdbc.spring;

import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.dao.DaoTypeEnum;
import com.framex.persistence.dao.jdbc.JdbcDao;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @date 2017/9/21 23:11
 * @description
 */
public class JdbcPrototypeDao implements JdbcDao {

    private DynamicDataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public JdbcPrototypeDao() {
        DataSource defaultDataSource = SpringContextUtil.getApplicationContext().getBean("dataSource", DataSource.class);
        if(defaultDataSource == null)
            throw new NullPointerException("JdbcPrototypeDao : no Default datasource found");
        else{
            dataSource = new DynamicDataSource().setDataSourceHolder("defaultDataSource");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    public JdbcPrototypeDao(String dataSourceBeanName) {
        DataSource DataSource = SpringContextUtil.getApplicationContext().getBean(dataSourceBeanName, DataSource.class);
        if(DataSource == null)
            throw new NullPointerException("JdbcPrototypeDao : no datasource with name " + dataSourceBeanName + "found");
        else{
            dataSource = new DynamicDataSource().setDataSourceHolder(dataSourceBeanName);
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    @Override
    public DaoTypeEnum getType() {
        return DaoTypeEnum.PROTOTYPE;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public boolean supportDynamicDataSource() {
        return true;
    }

    @Override
    public JdbcDao changeDataSource(String dataSourceBeanName) {
        if(SpringContextUtil.getApplicationContext().getBean(dataSourceBeanName) == null)
            throw new NullPointerException("No datasource with name " + dataSourceBeanName + " was found");
        this.dataSource.setDataSourceHolder(dataSourceBeanName);
        return this;
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

}
