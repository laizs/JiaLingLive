package com.gzzsc.lai.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @ClassName LogAspect
 * @Deacription Log注解aop切面
 * @Author laizs
 * @Date 2020/3/27 15:59
 **/
@Aspect
@Component
@Order(-5)//aop切面顺序，可为负数，越小级别越高；日志切面，配置的级别需要比较高，才能更好的记录方法的响应结果
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("@annotation(com.gzzsc.lai.annotation.Log)")
    public void annotationPointcut(){

    }
    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        // 此处进入到方法前  可以实现一些业务逻辑
        logger.info("日志：beforePointcut");
    }
    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint){
        logger.info("日志：afterPointcut");

    }
    @Around(value = "annotationPointcut()")
    public Object  doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        //获取参数名
        String[] parameterNames=methodSignature.getParameterNames();
        //获取参数名
        Object[] args=joinPoint.getArgs();
        Method method=methodSignature.getMethod();
        if(method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
            logger.info("RequestMapping url:"+requestMapping.value());
        }
        if(method.isAnnotationPresent(GetMapping.class)){
            GetMapping requestMapping=method.getAnnotation(GetMapping.class);
            logger.info("RequestMapping url:"+ JSON.toJSONString(requestMapping.value()) );
        }
        logger.info("执行方法：{}，参数:{},参数值：{}",methodSignature.toLongString(),parameterNames,args);
        Object result= null;//业务方法执行
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.info("执行方法：{}，异常：{}",methodSignature.toLongString(),throwable);

        }
        logger.info("执行方法：{}，返回结果:{}",methodSignature.toLongString(),result);
        return result;
    }

}
