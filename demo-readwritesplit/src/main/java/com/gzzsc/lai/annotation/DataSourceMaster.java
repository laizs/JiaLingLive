package com.gzzsc.lai.annotation;

import java.lang.annotation.*;

/**
 * @className DataSourceSlave
 * @Deacription 定义一个走主库库数据源的annotation
 * @Author laizs
 * @Date 2021/12/7 11:19
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceMaster {
}
