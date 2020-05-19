package com.gzzsc.lai.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Log
 * @Deacription 自定义log注解
 * @Author laizs
 * @Date 2020/4/30 17:03
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    public String value() default  "";
}
