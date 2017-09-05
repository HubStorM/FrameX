module com.framex.persistence {
    requires java.sql;
    requires spring.tx;
    requires spring.beans;
    requires spring.context;
    requires commons.dbcp2;
    requires java.management;
    requires spring.jdbc;
    exports com.framex.persistence;
}