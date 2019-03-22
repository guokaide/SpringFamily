package com.spring.data.mybatisdemo.mapper;


import com.spring.data.mybatisdemo.model.Coffee;
import org.apache.ibatis.annotations.*;



@Mapper
public interface CoffeeMapper {
    @Insert("insert into t_coffee (name, price, create_time, update_time)"
            + " values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    Long save(Coffee coffee);

    @Select("select * from t_coffee where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // 这其实是将数据库表中属性的表示形式转换为类对象属性的表示方式
            // map-underscore-to-camel-case = true 可以实现一样的效果
            // @Result(column = "update_time", property = "updateTime"),
    })
    Coffee findById(@Param("id") Long id);
}

