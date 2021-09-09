package com.gzzsc.lai;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * @className DemoApplicationTest
 * @Deacription TODO
 * @Author laizs
 * @Date 2021/9/1 17:47
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DemoApplicationTest {
    @Autowired
    private FileSystem fileSystem;
    @Value("${hadoop.namespace}")
    private String nameSpace;
    @Test
    public void contextLoads(){

    }
    @Test
    public void filesInfo() throws IOException {
        System.out.println("nameSpace:"+nameSpace);
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path(nameSpace), true);
        while (files.hasNext()){
            LocatedFileStatus fileStatus = files.next();
            log.info("名字: "+fileStatus.getPath().getName());
            log.info("文件分组: "+fileStatus.getGroup());
            log.info("文件长度: "+String.valueOf(fileStatus.getLen()));
            log.info("文件权限: "+String.valueOf(fileStatus.getPermission()));
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            log.info("block 数："+blockLocations.length);
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(fileStatus.getPath().getName()+"block主机节点："+host);
                }
            }
        }
    }
    @Test
    public void isFile() throws IOException {
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(nameSpace));
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()){
                log.info("文件："+fileStatus.getPath().getName());
            }
        }
    }
}
