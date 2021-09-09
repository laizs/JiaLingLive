package com.gzzsc.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className HadoopDemoApplication
 * @Deacription
 * @Author laizs
 * @Date 2021/8/30 17:38
 **/
@SpringBootApplication
public class HadoopDemoApplication {
    public static void main(String[] args) {
        //从网上下载含有winutils.exe和hadoop.dll文件，在windows环境下使用的包，并设置环境变量
        System.setProperty("hadoop.home.dir", "E:\\dev\\winutils-master\\hadoop-3.2.0");
        SpringApplication.run(HadoopDemoApplication.class,args);

    }
}
