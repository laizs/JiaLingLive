package com.gzzsc.lai.sharding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;



/**
 * 表分片，按表记录的日期字段，用于处理使用单一键作为分片键的BETWEEN AND进行分片的场景。需要配合StandardShardingStrategy使用。
 */
public class TableMonthRangeShardingAlgorithm implements RangeShardingAlgorithm<String> {
    private final static Logger LOGGER= LoggerFactory.getLogger(TableMonthRangeShardingAlgorithm.class);
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> rangeShardingValue) {
        LOGGER.info("availableTargetNames:{},rangeShardingValue{}", JSON.toJSONString(availableTargetNames),JSON.toJSONString(rangeShardingValue));
        Range<String> ranges= rangeShardingValue.getValueRange();
        LOGGER.info("valueRange:{}",JSON.toJSONString(ranges));
        if(ranges.hasLowerBound()){
            LOGGER.info("hasLowerBound:{},lowerEndpoint:{}",ranges.hasLowerBound(),ranges.lowerEndpoint());
        }
        if(ranges.hasUpperBound()){
            LOGGER.info("hasUpperBound:{},upperEndpoint:{}",ranges.hasUpperBound(),ranges.upperEndpoint());
        }
        return availableTargetNames;//这里暂定是简单处理，匹配所有的表
    }
}
