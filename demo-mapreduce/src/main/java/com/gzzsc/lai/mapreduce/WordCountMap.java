package com.gzzsc.lai.mapreduce;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;

/**
 * @className WordCountMap
 * @Deacription 单词统计  map
 * @Author laizs
 * @Date 2021/9/2 16:46
 **/
public class WordCountMap extends Mapper<Object,Text,Text,IntWritable> {
    /**
     *
     * @param key 默认情况下，是MapReduce所读取到的一行文本的起始偏移量
     * @param value 默认情况下，是MapReduce所读取到的一行文本的内容，hadoop中的序列化类型为Text
     * @param context 是用户自定义逻辑处理完成后输出的KEY，在此处是单词，String
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //防止中文乱码
        String line=new String(value.getBytes(),0,value.getLength(),"UTF-8").trim();
        if(StringUtils.isNotBlank(line)){
            byte[] btValue=line.getBytes();
            InputStream inputStream=new ByteArrayInputStream(btValue);
            Reader reader=new InputStreamReader(inputStream);
            IKSegmenter ikSegmenter=new IKSegmenter(reader,true);
            Lexeme lexeme;
            while ((lexeme=ikSegmenter.next())!=null){
                IntWritable one = new IntWritable(1);
                Text word=new Text();
                word.set(lexeme.getLexemeText());
                context.write(word,one);
            }
        }
    }
}
