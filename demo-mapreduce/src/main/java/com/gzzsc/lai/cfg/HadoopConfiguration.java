package com.gzzsc.lai.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @className HadoopConfiguration
 * @Deacription hadoop的配置
 * @Author laizs
 * @Date 2021/9/3 11:08
 **/
@Component
@Lazy(value = false)
public class HadoopConfiguration {
    private final static Logger LOGGER= LoggerFactory.getLogger(HadoopConfiguration.class);
    @Value("${hadoop.name-node}")
    private String nameNode;
    public org.apache.hadoop.conf.Configuration getConfiguration(){
        org.apache.hadoop.conf.Configuration conf=new org.apache.hadoop.conf.Configuration();
        conf.set("fs.defaultFs",nameNode);
        conf.set("dfs.relication","1");
        conf.set("mapred.job.tracker", nameNode);//设置mapreduce 主节点
        LOGGER.info("----HadoopConfiguration fs:"+conf.get("fs.defaultFs"));
        return conf;
    }
    public String getNameNode(){
        return nameNode;
    }
}
