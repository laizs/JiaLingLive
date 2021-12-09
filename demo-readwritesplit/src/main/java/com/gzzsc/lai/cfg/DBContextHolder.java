package com.gzzsc.lai.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className DBContextHolder
 * @Deacription 使用threadlocal将数据源绑定到当前线程上
 * @Author laizs
 * @Date 2021/12/7 10:00
 **/
public class DBContextHolder {
    private static final Logger logger= LoggerFactory.getLogger(DBContextHolder.class);
    private static final ThreadLocal<DBTypeEnum> contextHolder=new ThreadLocal<>();
    private static final AtomicInteger counter=new AtomicInteger();

    /**
     * 获取当前线程绑定数据源，获取不到则使用master
     * @return
     */
    public static DBTypeEnum get(){
        DBTypeEnum d= contextHolder.get()==null?DBTypeEnum.MASTER:contextHolder.get();
        logger.info("！！！当前数据源:"+d);
        return d;
    }

    /**
     * 设置
     * @param dbTypeEnum
     */
    public static void set(DBTypeEnum dbTypeEnum){
        contextHolder.set(dbTypeEnum);
    }

    /**
     * 设置主库
     */
    public static void master(){
        set(DBTypeEnum.MASTER);
        logger.info("！！！切换到master:");
    }

    /**
     * 设置从库，简单轮训方法
     */
    public static void slave(){
        int index=counter.getAndIncrement()%2;
        if(counter.get()>9999){
            counter.set(0);
        }
        if(index==0){
            set(DBTypeEnum.SLAVE1);
            logger.info("！！！切换到slave1:");
        }else{
            set(DBTypeEnum.SLAVE2);
            logger.info("！！！切换到slave2:");
        }
    }

    /**
     * 清除ThreadLocal
     */
    public static void clearDBType(){
        logger.info("！！！马上清除threadLocal数据源，当前:"+contextHolder.get());
        contextHolder.remove();
    }
}
