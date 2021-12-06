package com.gzzsc.lai.service.utils;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * @className SnowFlakeUtils
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/12/6 14:48
 **/
public class SnowFlakeUtils {
    public static Comparable<?>  getId(Long workerId) {
        if (!(workerId >= 0 && workerId < 1024L)) {
            throw new RuntimeException(String.format("workerId is not support range must be [0,1024)"));
        }
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        snowflakeShardingKeyGenerator.getProperties().put("worker.id", String.valueOf(workerId));
        Comparable<?> generateKey = snowflakeShardingKeyGenerator.generateKey();
        return generateKey;
    }

}
