package com.spring.data.mongodemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id // 将 String id 转换成 Mongodb 中的 Object id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
