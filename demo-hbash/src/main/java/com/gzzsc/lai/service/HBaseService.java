package com.gzzsc.lai.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className HBaseService
 * @Author laizs
 * @Date 2021/8/31 13:44
 **/
public class HBaseService {
    private final static Logger logger= LoggerFactory.getLogger(HBaseService.class);
    private Configuration conf=null;
    private Connection connection=null;
    public HBaseService (Configuration conf){
        this.conf=conf;
        try {
            this.connection= ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("获取Hbase连接失败",e);
        }
    }
    private void close(Admin admin, ResultScanner rs, Table table){
        if(admin!=null){
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭admin失败",e);
            }
        }
        if(rs!=null){
            rs.close();
        }
        if(table!=null){
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭table失败",e);
            }
        }
    }

    /**
     * 创建表
     * @param table
     * @param cf
     * @return
     */
    public boolean createTable(String table,String cf){
        Admin admin=null;
        try {
            //表管理器
            admin=connection.getAdmin();
            //定义表
            HTableDescriptor hTableDescriptor=new HTableDescriptor(TableName.valueOf(table));
            //定义族列
            HColumnDescriptor hColumnDescriptor=new HColumnDescriptor(cf);
            //将族列添加到表中
            hTableDescriptor.addFamily(hColumnDescriptor);
            //执行创建表操作
            admin.createTable(hTableDescriptor);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            this.close(admin,null,null);
        }
    }

    /**
     * 添加数据
     * @param tableName
     * @param rowKey
     * @param cf
     * @param columns
     * @param values
     */
    public void put(String tableName,String rowKey,String cf,String[] columns,String[] values){
        Table table=null;
        try {
            table=connection.getTable(TableName.valueOf(tableName));
            Put put=new Put(rowKey.getBytes());
            //添加列
            for(int i=0;i<columns.length;i++){
                put.addColumn(cf.getBytes(),columns[i].getBytes(),values[i].getBytes());
                table.put(put);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(null,null,table);
        }
    }

    /**
     * 查询数据
     * @param tableName
     * @param cf
     * @param rowKey
     * @return
     */
    public Map<String,String> get(String tableName, String cf, String rowKey){
        Map<String,String> result=new HashMap<>();
        Table table=null;
        try {
            table=this.connection.getTable(TableName.valueOf(tableName));
            Get get=new Get(Bytes.toBytes(rowKey));
            Result hTableResult=table.get(get);
            if(hTableResult!=null && !hTableResult.isEmpty()){
                for(Cell cell:hTableResult.listCells()){
                    result.put(Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength()),
                            Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询一行数据失败，tableName:{},rowKey:{}",tableName,rowKey);
        }finally {
            close(null,null,table);
        }
        return result;
    }
}
