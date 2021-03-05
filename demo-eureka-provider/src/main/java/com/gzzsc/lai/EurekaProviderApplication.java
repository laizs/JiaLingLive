package com.gzzsc.lai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName EurekaProviderApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/15 14:39
 **/
@SpringBootApplication
@EnableEurekaClient
public class EurekaProviderApplication {
    private  final  static Logger log= LoggerFactory.getLogger(EurekaProviderApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class,args);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    log.info("随意写点日志");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
