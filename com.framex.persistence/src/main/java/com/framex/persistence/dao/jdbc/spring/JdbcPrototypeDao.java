package com.framex.persistence.dao.jdbc.spring;

import com.framex.persistence.SpringContextUtil;
import com.framex.persistence.dao.DaoTypeEnum;
import com.framex.persistence.dao.jdbc.BeanInfoHelper;
import com.framex.persistence.dao.jdbc.JdbcDao;
import com.framex.persistence.dao.jdbc.SingletonBeanInfo;
import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    public int execute(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int execute(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.update(sql, args, argTypes);
    }



    /**
     * 多数据库分页支持
     * @param sql
     * @param currentPage
     * @param pageSize
     * @param requiredType
     * @param args
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> findList(String sql, int currentPage, int pageSize, Class<T> requiredType, Object... args) {
        sql += " LIMIT " + (currentPage - 1) * pageSize + ", " + pageSize;
        return findList(sql, requiredType, args);
    }

    @Override
    public <T> List<T> findList(String sql, int currentPage, int pageSize, Object[] args, int[] argTypes, Class<T> requiredType) {
        sql += " LIMIT " + (currentPage - 1) * pageSize + ", " + pageSize;
        return findList(sql, args, argTypes, requiredType);
    }

    @Override
    public List<Map<String, Object>> findList(String sql, int currentPage, int pageSize, Object... args) {
        sql += " LIMIT " + (currentPage - 1) * pageSize + ", " + pageSize;
        return findList(sql, args);
    }

    @Override
    public List<Map<String, Object>> findList(String sql, int currentPage, int pageSize, Object[] args, int[] argTypes) {
        sql += " LIMIT " + (currentPage - 1) * pageSize + ", " + pageSize;
        return findList(sql, args, argTypes);
    }

    @Override
    /**
     * 缓存优化
     */
    public <T> int insert(T item) {
        SingletonBeanInfo beanInfo= BeanInfoHelper.getSingletonBeanInfo(item.getClass());

        List<Object> args = new ArrayList<>();
        StringBuffer insertSubString = new StringBuffer("INSERT INTO " + beanInfo.getTableName() + " (");
        StringBuffer valuesSubString = new StringBuffer(" VALUES(");

        beanInfo.getReadWritePairMap().forEach((prop, methodPair) -> {
            insertSubString.append(prop).append(", ");
            try{
                args.add(methodPair.getReadMethod().invoke(item));
                valuesSubString.append("?, ");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        String executeSql = insertSubString.substring(0, insertSubString.length() - 2) + ")"
                + valuesSubString.substring(0, valuesSubString.length() - 2) + ")";
        return jdbcTemplate.update(executeSql, args.toArray());
    }

    @Override
    public <T> int update(T item) {
        SingletonBeanInfo beanInfo= BeanInfoHelper.getSingletonBeanInfo(item.getClass());

        List<Object> args = new ArrayList<>();
        StringBuffer updateSql = new StringBuffer("UPDATE " + beanInfo.getTableName() + " SET ");
        beanInfo.getReadWritePairMap().forEach((prop, methodPair) -> {
            updateSql.append(prop).append(" = ?, ");
            try{
                args.add(methodPair.getReadMethod().invoke(item));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        String executeSql = null;
        try {
            executeSql = updateSql.substring(0, updateSql.length() - 2)
                    + " WHERE "
                    + beanInfo.getPrimaryKeyName()
                    + " = ?";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            args.add(beanInfo.getReadWritePairMap().get(beanInfo.getPrimaryKeyName()).getReadMethod().invoke(item));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return jdbcTemplate.update(executeSql, args.toArray());
    }

    @Override
    public <T> int delete(T item) {
        SingletonBeanInfo beanInfo= BeanInfoHelper.getSingletonBeanInfo(item.getClass());
        String sql = "DELETE FROM "
                + beanInfo.getTableName()
                + " WHERE " + beanInfo.getPrimaryKeyName()
                + " = ?";
        Object key = null;
        try {
            key = beanInfo.getReadWritePairMap().get(beanInfo.getPrimaryKeyName()).getReadMethod().invoke(item);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return jdbcTemplate.update(sql, key);
    }

    @Override
    public <T> int delete(Class<T> requiredType, String key) {
        SingletonBeanInfo beanInfo= BeanInfoHelper.getSingletonBeanInfo(requiredType);
        String sql = null;
        sql = "DELETE FROM "
                + beanInfo.getTableName()
                + " WHERE " + beanInfo.getPrimaryKeyName()
                + " = ?";
        return jdbcTemplate.update(sql, key);
    }

    @Override
    public <T> T findObject(Class<T> requiredType, String key) {
        SingletonBeanInfo beanInfo = BeanInfoHelper.getSingletonBeanInfo(requiredType);
        String sql = "SELECT * FROM " + beanInfo.getTableName() + " WHERE " + beanInfo.getPrimaryKeyName() + " = ?";
        return findObject(sql, requiredType, key);
    }
}
