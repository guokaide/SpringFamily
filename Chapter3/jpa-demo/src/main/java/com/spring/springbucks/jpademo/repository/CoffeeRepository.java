package com.spring.springbucks.jpademo.repository;

import com.spring.springbucks.jpademo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
