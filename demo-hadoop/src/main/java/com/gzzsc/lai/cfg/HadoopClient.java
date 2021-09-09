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
 * @Deacription hadoop工具类
 * @Author laizs
 * @Date 2021/9/3 10:37
 **/
@Component
public class HadoopClient {
    private final static Logger logger= LoggerFactory.getLogger(HadoopClient.class);
    @Autowired
    private FileSystem fileSystem;
    @Value("${nameNode}")
    private String nameNode;

    /**
     * 删除文件或目录
     * @param path
     * @param fileName
     */
    private void rmdir(String path,String fileName){
        try {
            if(StringUtils.isNoneBlank(nameNode)){
                path=nameNode+path;
            }
            if(StringUtils.isNoneBlank(fileName)){
                path=path+ "/"+fileName;
                fileSystem.delete(new Path(path),true);
            }
        }catch (Exception e){
            logger.error("删除文件或目录异常",e);
        }

    }
}
