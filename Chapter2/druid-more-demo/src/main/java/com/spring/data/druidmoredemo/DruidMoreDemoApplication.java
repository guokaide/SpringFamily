package com.spring.data.druidmoredemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement(proxyTargetClass = true)
public class DruidMoreDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private FooService fooService;




    public static void main(String[] args) {
        SpringApplication.run(DruidMoreDemoApplication.class, args);
    }

    // 监控慢SQL的执行
    // 2个线程同时对同一条记录做锁定，势必会有一个等待另外一个释放这个锁
    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
        new Thread(() -> fooService.selectForUpdate()).start();
        new Thread(() -> fooService.selectForUpdate()).start();

    }
}
