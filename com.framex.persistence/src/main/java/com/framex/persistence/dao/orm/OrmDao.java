package com.framex.persistence.dao.orm;

import com.framex.persistence.dao.DaoTypeEnum;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * @author lijie
 * @date 2017/10/24 11:22
 * @description
 */
public interface OrmDao {
    DaoTypeEnum getType();
    DataSource getDataSource();
    <T> T findObject(Class<T> requiredType, Serializable id);
    <T> T findObject(String hql, Class<T> requiredType, Object... args);
    <T> List<T> findList(Class<T> requiredType);
    <T> List<T> findList(String hql, Class<T> requiredType, Object... args);
    <T> void save(T item);
    <T> void update(T item);
}
