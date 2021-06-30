package com.gzzsc.lai.cfg;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className FeignConfiguration
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/3/12 11:12
 **/
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
