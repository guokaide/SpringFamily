package com.spring.data.simplejdbcdemo;

import lombok.Builder;
import lombok.Data;

@Data      // 省去get() set()
@Builder   // 提供构造方法
public class Foo {
    private Long id;
    private String bar;
}
