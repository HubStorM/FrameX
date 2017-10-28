package com.framex.persistence.dao.jdbc;

import java.lang.reflect.Method;

/**
 * @author lijie
 * @date 2017/10/25 21:53
 * @description
 */
public class ReadWritePair {
    private Method readMethod;
    private Method writeMethod;

    public ReadWritePair() {
    }

    public ReadWritePair(Method readMethod, Method writeMethod) {
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }
}
