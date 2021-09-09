package com.gzzsc.lai.mapreduce;

import com.gzzsc.lai.cfg.HadoopClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className MapReduceClient
 * @Deacription mapreduce客户端
 * @Author laizs
 * @Date 2021/9/3 10:14
 **/
@Component
public class MapReduceClient {
    private final static Logger logger= LoggerFactory.getLogger(MapReduceClient.class);
    //默认reduce输出目录
    private static final String OUTPUT_PATH="/output";
    @Autowired
    private ReduceJobsUtil reduceJobsUtil;
    @Autowired
    private HadoopClient hadoopClient;

    /**
     * 单词个数统计
     * @param jobName
     * @param inputPath 文件路径，如果是文件夹，则默认粉刺该文件下下所有的文件
     */
    public void wordCount(String jobName,String inputPath) throws InterruptedException, IOException, ClassNotFoundException {
        String outputPath=OUTPUT_PATH+"/"+jobName;
        hadoopClient.rmdir(outputPath,null);
        reduceJobsUtil.getWordCountJobsConf(jobName,inputPath,outputPath);
    }

}
