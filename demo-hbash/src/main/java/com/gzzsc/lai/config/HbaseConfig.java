package com.gzzsc.lai.config;

import com.gzzsc.lai.service.HBaseService;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className HbaseConfig
 * @Deacription peizhi
 * @Author laizs
 * @Date 2021/8/31 13:30
 **/
@Configuration
public class HbaseConfig {
    @Value("${Hbase.nodes}")
    private String nodes;
    @Value("${Hbase.maxsize}")
    private String maxsize;
    @Bean
    public HBaseService getHbaseService(){
        org.apache.hadoop.conf.Configuration conf= HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum",nodes);
        conf.set("hbase.client.keyvalue.maxsize",maxsize);
        return new HBaseService(conf);
    }
}
