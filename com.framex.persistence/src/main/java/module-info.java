module com.framex.persistence {
    requires java.sql;
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
    exports com.framex.persistence;
}