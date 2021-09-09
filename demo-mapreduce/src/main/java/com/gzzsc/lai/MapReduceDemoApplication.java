package com.gzzsc.lai;

import com.gzzsc.lai.mapreduce.MapReduceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className MapReduceDemoApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/9/2 16:25
 **/
@SpringBootApplication
@Slf4j
public class MapReduceDemoApplication implements CommandLineRunner {
    @Autowired
    private MapReduceClient mapReduceClient;
    public static void main(String[] args) {
        //window环境下运行hadoop程序，需要设置环境变量含有winutils.exe程序的hadoop home
        System.setProperty("hadoop.home.dir", "E:\\dev\\winutils-master\\hadoop-3.2.0");
        SpringApplication.run(MapReduceDemoApplication.class,args);
    }
    //启动完执行
    @Override
    public void run(String... args) throws Exception {
        log.info("系统加载完执行...运行mapreduce程序");
        String jobName="mr1"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        this.mapReduceClient.wordCount(jobName,"/wc_input");
    }
}
