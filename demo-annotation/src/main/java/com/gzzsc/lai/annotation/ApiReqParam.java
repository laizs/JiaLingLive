package com.gzzsc.lai.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ApiReq
 * @Deacription api请求注解
 * @Author laizs
 * @Date 2020/5/6 13:33
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiReqParam {

}
