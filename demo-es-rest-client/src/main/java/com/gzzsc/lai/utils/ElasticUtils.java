package com.gzzsc.lai.utils;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ElasticUtils
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/14 11:03
 **/
@Slf4j
public class ElasticUtils {
    private ElasticUtils(){

    }

    /**
     *
     * @param className
     * @return
     */
    public static Class<?>  getClazz(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 搜索
     * @param queryBuilder 查询对象
     * @param from 设置from选项，确定要开始搜索的结果索引。 默认为0。
     * @param size 置大小选项，确定要返回的搜索匹配数。 默认为10。
     * @param timeOut
     * @return
     */
    public static SearchSourceBuilder initSearchSourceBuilder(QueryBuilder queryBuilder,int from,int size,int timeOut){
        //使用默认选项创建 SearchSourceBuilder 。
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //设置查询对象。可以使任何类型的 QueryBuilder
        sourceBuilder.query(queryBuilder);
        //设置from选项，确定要开始搜索的结果索引。 默认为0。
        sourceBuilder.from(from);
        //设置大小选项，确定要返回的搜索匹配数。 默认为10。
        sourceBuilder.size(size);
        sourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));
        return sourceBuilder;
    }

    /**
     *
     * @param queryBuilder
     * @return
     */
    public static SearchSourceBuilder initSearchBuilder(QueryBuilder queryBuilder){
        return initSearchSourceBuilder(queryBuilder,0,10,60);
    }
}
