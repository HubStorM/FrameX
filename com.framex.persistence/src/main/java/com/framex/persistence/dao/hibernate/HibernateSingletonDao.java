package com.framex.persistence.dao.hibernate;

import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.dao.Dao;
import com.framex.persistence.dao.DaoTypeEnum;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author lijie
 * @date 2017/10/22 21:26
 * @description
 */
public class HibernateSingletonDao implements Dao{

    private SessionFactory sessionFactory;

    public HibernateSingletonDao(DaoTypeEnum daoTypeEnum) {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        if(daoTypeEnum.equals(DaoTypeEnum.SINGLETON)){
            DataSource defaultDataSource = SpringContextUtil.getApplicationContext().getBean("defaultDataSource", DataSource.class);
            lsfb.setDataSource(defaultDataSource);
        }
        if(daoTypeEnum.equals(DaoTypeEnum.PROTOTYPE)){
            DynamicDataSource dynamicDataSource = new DynamicDataSource().setDataSourceHolder("defaultDataSource");
            lsfb.setDataSource(dynamicDataSource);
        }
        Properties props = new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        lsfb.setHibernateProperties(props);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DaoTypeEnum getType() {
        return null;
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }

    @Override
    public boolean supportDynamicDataSource() {
        return false;
    }

    @Override
    public Dao changeDataSource(String dataSourceBeanName) {
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

    @Override
    public <T> void update(T item) {

    }
}
