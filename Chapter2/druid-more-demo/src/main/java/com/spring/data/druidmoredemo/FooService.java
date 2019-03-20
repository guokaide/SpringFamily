package com.spring.data.druidmoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void selectForUpdate() {
        jdbcTemplate.queryForObject("SELECT ID FROM FOO WHERE ID = 1 FOR UPDATE", Long.class);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
    }
}
