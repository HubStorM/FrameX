package com.framex.persistence;

import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import com.framex.persistence.framexconfig.FramexConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Component("testservice")
public class TestService {
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DynamicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PlatformTransactionManager tx;

    public void main(){
        jdbcTemplate.execute("insert into framex_config values('1', '1', '1')");
    }

    public void tx(){
        TransactionTemplate txTemplate = new TransactionTemplate(tx);
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                System.out.println(sessionFactory.getCurrentSession().get(FramexConfig.class, "1"));
            }
        });
    }

    public void list(){
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT * FROM framex_config");

    }



}
