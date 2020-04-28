package com.gzzsc.lai.controller.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyProperties
 * @Deacription 自定义配置
 * @Author laizs
 * @Date 2020/3/19 15:54
 **/
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "mypro")
public class MyProperties {
    private String name;
    private Integer age;
    private String email;
    private String qq;
}
