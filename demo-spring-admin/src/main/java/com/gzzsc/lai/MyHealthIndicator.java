package com.gzzsc.lai;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @className MyHealthIndicator
 * @Deacription 自定义状态
 * @Author laizs
 * @Date 2021/4/22 11:18
 **/
@Component
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("自定义状态","OK");
    }
}
