package com.gzzsc.lai.utils;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @className SnowFlakeUtils
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/12/6 14:48
 **/
public class SnowFlakeUtils {
    private static  SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
    static {
        InetAddress inet = null;
        try {
            inet = InetAddress.getLocalHost();
            String hostAddress = inet.getHostAddress();
            System.out.println("hostAddress:"+hostAddress);
            long workId =  Long.parseLong(hostAddress.replace(".", ""));
            // workId范围只能是[0,1024)
            workId=workId%1024;
            SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
            snowflakeShardingKeyGenerator.getProperties().put("worker.id", String.valueOf(workId));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成一个id,利用shardingsphere提供的雪花算法，worker.id的生成利用本地ip地址，去掉.，再对1024取摸得到
     */
    public static long getId() {
        /*if (!(workerId >= 0 && workerId < 1024L)) {
            throw new RuntimeException(String.format("workerId is not support range must be [0,1024)"));
        }*/

        Comparable<?> generateKey = snowflakeShardingKeyGenerator.generateKey();
        System.out.println("--------------------id-----------------------------");
        System.out.println(generateKey);
        return (Long) generateKey;

    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("雪花id:"+getId());
        System.out.println("雪花id:"+getId());
    }

}
