package com.spring.data.transactionpropagationdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class TransactionPropagationDemoApplication implements CommandLineRunner {
    @Autowired
    private FooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(TransactionPropagationDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            fooService.invokeInsertThenRollback();
        } catch (Exception e) {

        }
        log.info("AAA {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAA'", Long.class));
        log.info("BBB {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBB'", Long.class));
    }
}
