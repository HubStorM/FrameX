module com.framex.persistence {
    requires java.sql;
    requires spring.tx;
    requires spring.beans;
    requires spring.context;
    exports com.framex.persistence;
}