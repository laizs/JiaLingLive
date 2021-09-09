package com.gzzsc.lai.cfg;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @className HadoopConfig
 * @Deacription hadoop配置类
 * @Author laizs
 * @Date 2021/9/1 16:37
 **/
@Configuration
@ConditionalOnProperty(name="hadoop.name-node")
@Slf4j
public class HadoopConfig {
    @Value("${hadoop.name-node}")
    private String nameNode;
    @Bean("fileSystem")
    public FileSystem createFs() throws URISyntaxException, IOException, InterruptedException {
        org.apache.hadoop.conf.Configuration conf=new org.apache.hadoop.conf.Configuration();
        conf.set("fs.defaultFs",nameNode);
        conf.set("dfs.relication","1");
        URI uri=new URI(nameNode.trim());
        FileSystem fs=FileSystem.get(uri,conf,"root");
        log.info("fileSystem 加载了");
        System.out.println("fs.defaultFs:"+conf.get("fs.defaultFs"));
        return fs;
    }
}
