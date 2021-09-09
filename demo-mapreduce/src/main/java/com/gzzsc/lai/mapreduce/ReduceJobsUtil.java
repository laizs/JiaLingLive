package com.gzzsc.lai.mapreduce;

import com.gzzsc.lai.MapReduceDemoApplication;
import com.gzzsc.lai.cfg.HadoopConfig;
import com.gzzsc.lai.cfg.HadoopConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className ReduceJobsUtil
 * @Deacription mapreduce作业任务提交工具类
 * @Author laizs
 * @Date 2021/9/2 17:24
 **/
@Component
public class ReduceJobsUtil {
    private final static Logger LOGGER= LoggerFactory.getLogger(ReduceJobsUtil.class);
    @Autowired
    private HadoopConfiguration hadoopConfiguration;
    @Autowired
    private  HadoopConfig hadoopConfig;
    public void getWordCountJobsConf(String jobName,String inputPath,String outputPath) throws IOException, ClassNotFoundException, InterruptedException {
        //需要次方法里new Configuration ,从hadoopConfiguration.getConfiguration()获取的话运行有问题，原因未知
        Configuration conf=new Configuration();
        conf.set("dfs.replication", "1");
        conf.set("fs.defaultFS", hadoopConfiguration.getNameNode());
        conf.set("mapred.job.tracker", hadoopConfiguration.getNameNode());
        LOGGER.info("------fs:"+hadoopConfiguration.getConfiguration().get("fs.defaultFs"));
        Job job=Job.getInstance(conf,jobName);
        job.setJarByClass(MapReduceDemoApplication.class);
        job.setMapperClass(WordCountMap.class);
        job.setCombinerClass(WordCountReduce.class);
        job.setReducerClass(WordCountReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 小文件合并设置
//        job.setInputFormatClass(CombineTextInputFormat.class);
//        // 最大分片
//        CombineTextInputFormat.setMaxInputSplitSize(job, 4 * 1024 * 1024);
//        // 最小分片
//        CombineTextInputFormat.setMinInputSplitSize(job, 2 * 1024 * 1024);
        FileInputFormat.addInputPath(job,new Path(inputPath));
        FileOutputFormat.setOutputPath(job,new Path(outputPath));
        //提交程序，并监控打印程序执行情况
        job.waitForCompletion(true);

    }
}
