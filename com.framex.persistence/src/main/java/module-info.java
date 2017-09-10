module com.framex.persistence {
    requires spring.tx;
    requires spring.beans;
    requires spring.context;
    requires commons.dbcp2;
    requires java.management;
    requires spring.jdbc;
    requires spring.core;
    requires c3p0;
    requires java.naming;
    requires hibernate.jpa;
    requires spring.orm;
    requires hibernate.core;
    requires java.sql;
    exports com.framex.persistence;
}