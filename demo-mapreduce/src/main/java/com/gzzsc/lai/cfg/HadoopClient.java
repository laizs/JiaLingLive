package com.gzzsc.lai.cfg;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @className HadoopClient
 * @Deacription hadoop工具类，用于hdfs系统的访问
 * @Author laizs
 * @Date 2021/9/3 10:37
 **/
@Component
public class HadoopClient {
    private final static Logger logger= LoggerFactory.getLogger(HadoopClient.class);
    @Autowired
    private FileSystem fileSystem;
    @Autowired
    private HadoopConfiguration hadoopConfiguration;

    /**
     * 删除文件或目录
     * @param path
     * @param fileName
     */
    public void rmdir(String path,String fileName){
        try {
            if(StringUtils.isNoneBlank(hadoopConfiguration.getNameNode())){
                path=hadoopConfiguration.getNameNode()+path;
            }
            if(StringUtils.isNoneBlank(fileName)){
                path=path+ "/"+fileName;
            }
            logger.info("删除目录："+path);
            fileSystem.delete(new Path(path),true);
        }catch (Exception e){
            logger.error("删除文件或目录异常",e);
        }

    }
}
