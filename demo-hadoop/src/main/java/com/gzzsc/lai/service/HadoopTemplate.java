package com.gzzsc.lai.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @className HadoopTemplate
 * @Deacription hadoop操作类
 * @Author laizs
 * @Date 2021/9/1 16:49
 **/
@Component
@ConditionalOnClass(FileSystem.class)
@Slf4j
public class HadoopTemplate {
    @Autowired
    private FileSystem fileSystem;
    @Value("${hadoop.name-node}")
    private String nameNode;
    @Value("${hadoop.namespace:/}")
    private String nameSpace;
    @PostConstruct
    public void init(){
        existDir(nameSpace,true);
    }
    public void uploadFile(String srcFile){
        copyFile2HDFS(false,true,srcFile,nameSpace);
    }
    public void uploadFile(boolean del,String srcFile){
        copyFile2HDFS(del,true,srcFile,nameSpace);
    }
    public void uploadFile(String srcFile,String destPath){
        copyFile2HDFS(false,true,srcFile,destPath);
    }
    public void uploadFile(boolean del,String srcFile,String destPath){
        copyFile2HDFS(del,true,srcFile,destPath);
    }
    public void delFile(String fileName){
        rmdir(nameSpace,fileName); ;
    }

    public void delDir(String path){
        nameSpace = nameSpace + "/" +path;
        rmdir(path,null) ;
    }

    public void download(String fileName,String savePath){
        getFile(nameSpace+"/"+fileName,savePath);
    }

    /**
     * 判断nameSpace 目录是否存在，如果不存在就创建该目录
     * @param filePath
     * @param create
     */
    private boolean existDir(String filePath, boolean create) {
        boolean flag=false;
        try {
            Path path=new Path(filePath);
            if(create){
                if(!fileSystem.exists(path)){
                    fileSystem.mkdirs(path);
                }
            }
            if(fileSystem.isDirectory(path)){
                flag=true;
            }
        }catch (Exception e){
            log.error("判断hdfs目录是否存在异常",e);
        }
        return flag;
    }

    /**
     * 上传文件至hdfs
     * @param delSrc 是否删除源文件
     * @param overwite 是否重写
     * @param srcFile 源文件路径
     * @param destPath hdfs目的路径
     */
    private void copyFile2HDFS(boolean delSrc,boolean overwite,String srcFile,String destPath){
        Path srcPath=new Path(srcFile);
        log.info("@@@@ 上传文件路径：{}",srcFile);
        //目的路径
        if(StringUtils.isNoneBlank(nameNode)){
            destPath=nameNode+destPath;
        }
        Path dstPath=new Path(destPath);
        try {
            log.info("@@@@ 开始上传");
            fileSystem.copyFromLocalFile(delSrc,overwite,srcPath,dstPath);
            log.info("@@@@ 上传完成");
        }catch (IOException e){
            log.info("上传文件至hdfs错误",e);
        }
    }

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
            log.error("删除文件或目录异常",e);
        }

    }

    /**
     * 从hdfs下载文件
     * @param hdfsFile
     * @param destPath 本地存放地址
     */
    public void getFile(String hdfsFile,String destPath){
        if(StringUtils.isNoneBlank(nameNode)){
            hdfsFile=nameNode+hdfsFile;
        }
        Path hdfsPath=new Path(hdfsFile);
        Path dstPath=new Path(destPath);
        try {
            log.info("@@@@ 下载上传");
            fileSystem.copyToLocalFile(hdfsPath,dstPath);
            log.info("@@@@ 下载完成");
        }catch (IOException e){
            log.error("下载hdfs文件失败",e);
        }
    }
    public String getNameNode(){
        return nameNode;
    }
}
