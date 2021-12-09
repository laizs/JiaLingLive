package com.gzzsc.lai.aspect;

import com.gzzsc.lai.annotation.DataSourceSlave;
import com.gzzsc.lai.cfg.DBContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @className DataSourceSelectAspect
 * @Deacription datasouce切换选择的切换拦截
 * @Author laizs
 * @Date 2021/12/7 11:22
 **/
@Aspect
@Component
@Order(-5)//aop切面顺序，可为负数，越小级别越高；要高于事务注解
public class DataSourceSelectAspect {
    private final static Logger logger= LoggerFactory.getLogger(DataSourceSelectAspect.class);
    @Pointcut("@annotation(com.gzzsc.lai.annotation.DataSourceSlave)")
    public void annotationPointcut(){

    }
    @Around(value = "annotationPointcut()")
    public Object setRead(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("检测到annotation dataSourceSlave---");
        try{
            DBContextHolder.slave();
            return joinPoint.proceed();
        }finally {
            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DBContextHolder.clearDBType();
            logger.info("清除threadLocal");
        }
    }
    @Pointcut("@annotation(com.gzzsc.lai.annotation.DataSourceMaster)")
    public void annotationPointcutMstaer(){

    }
    @Around(value = "annotationPointcutMstaer()")
    public Object setMaster(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("检测到annotation DataSourceMaster---");
        try{
            DBContextHolder.master();
            return joinPoint.proceed();
        }finally {
            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DBContextHolder.clearDBType();
            logger.info("清除threadLocal");
        }
    }
}
