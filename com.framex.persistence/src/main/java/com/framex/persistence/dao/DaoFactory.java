package com.framex.persistence.dao;

/**
 * @author lijie
 * @date 2017/9/21 15:49
 * @description
 */
public class DaoFactory {

    public static Dao getDao(DaoTypeEnum type){
        switch(type){
            case SINGLETON:
                return DefaultDao.INSTANCE;
            case PROTOTYPE:
                return new PrototypeDao();
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Dao getDao(String dataSourceName){
        return new PrototypeDao(dataSourceName);
    }
}
