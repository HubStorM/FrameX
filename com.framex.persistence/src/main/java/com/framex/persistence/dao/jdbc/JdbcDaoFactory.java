package com.framex.persistence.dao.jdbc;

import com.framex.persistence.dao.DaoTypeEnum;
import com.framex.persistence.dao.jdbc.spring.JdbcPrototypeDao;
import com.framex.persistence.dao.jdbc.spring.JdbcSingletonDao;

/**
 * @author lijie
 * @date 2017/9/21 15:49
 * @description
 */
public class JdbcDaoFactory {

    public static JdbcDao getDao(DaoTypeEnum type){
        switch(type){
            case SINGLETON:
                return JdbcSingletonDao.INSTANCE;
            case PROTOTYPE:
                return new JdbcPrototypeDao();
            default:
                throw new IllegalArgumentException();
        }
    }

    public static JdbcDao getDao(String dataSourceName){
        return new JdbcPrototypeDao(dataSourceName);
    }
}
