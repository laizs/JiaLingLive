package com.gzzsc.lai.sharding;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * @ClassName MyKeyGenrater
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/2 14:57
 **/
public class MyKeyGenrater implements ShardingKeyGenerator {
    @Override
    public Comparable<?> generateKey() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
