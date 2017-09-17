package com.framex.persistence.dao;

import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author lijie
 * @date 2017/9/15 13:41
 * @description
 */
public class DefaultDao implements Dao{

    private DynamicDataSource dynamicDataSource;

    private JdbcTemplate jdbc;

    public DefaultDao() {
        DataSource dataSource = SpringContextUtil.getApplicationContext().getBean("defaultDataSource", DataSource.class);
        DynamicDataSource defaultDynamicDataSource = SpringContextUtil.getApplicationContext().getBean("defaultDynamicDataSource", DynamicDataSource.class);
        JdbcTemplate jdbc = SpringContextUtil.getApplicationContext().getBean("defaultJdbcTemplate", JdbcTemplate.class);
        if(dataSource == null){
            throw new IllegalStateException("No default dataSource found.");
        }
        else{
            if(defaultDynamicDataSource == null){
                this.dynamicDataSource = new DynamicDataSource().setDataSourceHolder("defaultDataSource");
                if(jdbc == null){
                    this.jdbc = new JdbcTemplate(this.dynamicDataSource);
                }
                else{
                    this.jdbc = jdbc;
                }
            }
            else{
                this.dynamicDataSource = defaultDynamicDataSource;
                if(jdbc == null){
                    this.jdbc = new JdbcTemplate(this.dynamicDataSource);
                }
                else{
                    this.jdbc = jdbc;
                }
            }
        }
    }

    public DefaultDao(DynamicDataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    public DefaultDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean supportJdbcTemplate() {
        return true;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbc;
    }

    @Override
    public boolean supportChangeDataSource() {
        return isShared();
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public void setIsShared(boolean isShared) {

    }

    @Override
    public DataSource changeDataSource(String name) {

        return null;
    }

    @Override
    public DataSource changeDataSource(DataSource name) {
        return null;
    }


}
