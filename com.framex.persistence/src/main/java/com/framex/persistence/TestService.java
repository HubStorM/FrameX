package com.framex.persistence;

import com.framex.persistence.datasource.dynamic.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component("testservice")
public class TestService {
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DynamicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void main(){
        jdbcTemplate.execute("insert into framex_config values('1', '1', '1')");
    }


}
