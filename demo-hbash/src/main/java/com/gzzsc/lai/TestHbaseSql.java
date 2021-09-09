package com.gzzsc.lai;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gzzsc.lai.service.HBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * @className TestHbaseSql
 * @Author laizs
 * @Date 2021/8/31 14:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestHbaseSql {
    private final static Logger logger= LoggerFactory.getLogger(TestHbaseSql.class);
    @Autowired
    private HBaseService hBaseService;
    @Test
    public void test(){
        String table_name="test-persion";
        String cf="cf";
       // hBaseService.createTable(table_name,cf);
        //插入数据
        hBaseService.put(table_name,"01",cf,new String[]{"id","name"},new String[]{"01","张三"});
        hBaseService.put(table_name,"02",cf,new String[]{"id","name"},new String[]{"02","李四"});
        hBaseService.put(table_name,"03",cf,new String[]{"id","name"},new String[]{"03","王五"});
        //查询
        Map<String,String> result=this.hBaseService.get(table_name,cf,"02");
        System.out.println("数据："+new Gson().toJson(result));
        logger.info("数据:{}", new Gson().toJson(result));


    }
}
