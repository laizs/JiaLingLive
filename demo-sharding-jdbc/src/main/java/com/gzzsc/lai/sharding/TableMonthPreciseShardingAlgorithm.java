package com.gzzsc.lai.sharding;

import com.alibaba.fastjson.JSON;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * @ClassName TableDatePreciseShardingAlgorithm
 * @Deacription 表分片，按表记录的日期字段，获取月份，按月份（01-12）分表算法
 * @Author laizs
 * @Date 2020/6/30 17:43
 **/
public class TableMonthPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    private final static Logger LOGGER= LoggerFactory.getLogger(TableMonthPreciseShardingAlgorithm.class);
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> preciseShardingValue) {
        //availableTargetNames目标表名集合["login_record_1","login_record_2","login_record_3","login_record_4","login_record_5","login_record_6","login_record_7","login_record_8","login_record_9","login_record_10","login_record_11","login_record_12"]
        //分片字段信息preciseShardingValue ,preciseShardingValueL{"columnName":"date_str","logicTableName":"login_record","value":"2020-07-01"}
        LOGGER.info("collection:{},preciseShardingValueL{}", JSON.toJSONString(availableTargetNames),JSON.toJSONString(preciseShardingValue));
        LOGGER.info("分片字段：{},分片值：{}",preciseShardingValue.getColumnName(),preciseShardingValue.getValue());
        String shardingValue=preciseShardingValue.getValue();//分片值，日期，格式：yyyy-MM-dd
        Date date= null;
        String result=null;
        try {
            date = sdf.parse(shardingValue);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            int month=calendar.get(Calendar.MONTH)+1;//默认从0开始取
            String shardingTableName=preciseShardingValue.getLogicTableName()+"_"+month;
            LOGGER.info("按月份匹配，查找匹配的表名：{}",shardingTableName);
            for(String tableName:availableTargetNames){
                if(tableName.equals(shardingTableName)){//匹配表名
                    result= shardingTableName;
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("按日期匹配表名失败，异常：{}",e);

        }
        //健壮性处理，如果根据日期的月份匹配表名不成功，则使用haseCode来计算
        if(StringUtils.isEmpty(result)){
            int hashCode=shardingValue.hashCode();
            int tableSize=availableTargetNames.size();
            int tmp=hashCode % tableSize;//取模
            tmp=tmp+1;//因为数据库表名是从1开始排，而取模是从0开始，所以对应+1
            String shardingTableName=preciseShardingValue.getLogicTableName()+"_"+tmp;
            LOGGER.info("哈希计算，查找匹配的表名：{}",shardingTableName);
            for(String tableName:availableTargetNames){
                if(tableName.equals(shardingTableName)){//匹配表名
                    result= shardingTableName;
                    break;
                }
            }
        }


        return result;
    }
}
