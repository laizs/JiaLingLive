package com.gzzsc.lai.cfg;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @className MyRoutingDataSource
 * @Deacription AbstractRoutingDataSource实现类，主要通过determineCurrentLookupKey，决定使用哪个数据源
 * @Author laizs
 * @Date 2021/12/7 9:58
 **/
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //直接从当前线程获取主库或从库的标识
        return DBContextHolder.get();
    }
}
