package com.gzzsc.lai.mapreduce;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @className WordCountReduce
 * @Deacription map内容传入到reduce
 * @Author laizs
 * @Date 2021/9/2 17:15
 **/
@Slf4j
public class WordCountReduce extends Reducer<Text, IntWritable,Text,IntWritable> {
    /**
     *
     * @param key 第一个Text: 是传入的单词名称，是Mapper中传入的
     * @param values 是该单词出现了多少次，map出来的结果是每个单词都是1
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for(IntWritable val:values){
            sum+=val.get();
        }
        IntWritable result=new IntWritable();
        result.set(sum);
        if(null!=key && result!=null){
            context.write(key,result);
            String keyStr=key.toString();
            // 使用分词器，内容已经被统计好了，直接输出即可
            log.info("============ " + keyStr + " 统计分词为: " + sum + " ============");
        }

    }
}
