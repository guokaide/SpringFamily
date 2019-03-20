package com.spring.data.declarativetransactiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();

    }

    // 本身insertThenRollback()这个方法没有事务，真正执行事务的是Spring提供的代理类。因此其内部方法也没有真正启用事务
    // 如何解决呢？
    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        // insertThenRollback();
        fooService.insertThenRollback(); // 解决：直接调用FooService的方法，这是因为这是通过接口代理的
    }
}
