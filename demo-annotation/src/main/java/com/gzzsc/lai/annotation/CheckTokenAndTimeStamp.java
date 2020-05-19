package com.gzzsc.lai.annotation;

import java.lang.annotation.*;

/**
 * @ClassName CheckTokenAndTimeStamp
 * @Deacription 验证token和timeStamp
 * @Author laizs
 * @Date 2020/5/6 13:21
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckTokenAndTimeStamp {
    public String value() default  "";
}
