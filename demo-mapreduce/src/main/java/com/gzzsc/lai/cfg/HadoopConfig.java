package com.gzzsc.lai.cfg;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@ConditionalOnBean(HadoopConfiguration.class)
@Slf4j
public class HadoopConfig {
    @Autowired
    private HadoopConfiguration hadoopConfiguration;
    @Bean("fileSystem")
    public FileSystem createFs() throws URISyntaxException, IOException, InterruptedException {

        URI uri=new URI(hadoopConfiguration.getNameNode().trim());
        FileSystem fs=FileSystem.get(uri,hadoopConfiguration.getConfiguration(),"root");
        log.info("fileSystem 加载了");
        System.out.println("fs.defaultFs:"+hadoopConfiguration.getConfiguration().get("fs.defaultFs"));
        return fs;
    }
}
