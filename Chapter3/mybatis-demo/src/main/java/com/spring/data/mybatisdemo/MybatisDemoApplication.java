package com.spring.data.mybatisdemo;

import com.spring.data.mybatisdemo.mapper.CoffeeMapper;
import com.spring.data.mybatisdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.spring.data.mybatisdemo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee c = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();

        Long id = coffeeMapper.save(c);
        log.info("Coffee {} => {}", id, c);

        c = coffeeMapper.findById(id);
        log.info("Coffee {}", c);

    }
}
