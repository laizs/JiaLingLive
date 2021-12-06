package com.gzzsc.lai.service;

import com.alibaba.fastjson.JSON;
import com.gzzsc.lai.provider.entity.ElasticEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @ClassName BaseElasticService
 * @Deacription elatissearch操作封装
 * @Author laizs
 * @Date 2020/9/9 17:27
 **/
@Service("baseElasticService")
@Slf4j
public class BaseElasticService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @param idxName 索引名称
     * @param idxSQL 索引描述
     */
    public void createIndex(String idxName,String idxSQL){
        try {
            if(!this.indexExist(idxName)){
                log.error("idxName={}已经存在，idxSQL={}",idxName,idxSQL);
                return;
            }
            CreateIndexRequest request=new CreateIndexRequest(idxName);
            buildSetting(request);
            request.mapping(idxSQL, XContentType.JSON);
            CreateIndexResponse res=restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if(!res.isAcknowledged()){
                throw new RuntimeException("初始化失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * 设置分片
     * @param request
     */
    private void buildSetting(CreateIndexRequest request) {
        Map settingMap=new HashMap();
        //setting自定义分词器
        Map analysis=new HashMap<>();
        Map analyzer=new HashMap();
        analysis.put("analyzer",analyzer);
        Map pinyin_analyzer=new HashMap();
        pinyin_analyzer.put("tokenizer","my_pinyin");
        analyzer.put("pinyin_analyzer",pinyin_analyzer);

        Map tokenizer=new HashMap();
        analysis.put("tokenizer",tokenizer);
        Map my_pinyin=new HashMap();
        tokenizer.put("my_pinyin",my_pinyin);
        my_pinyin.put("type","pinyin");
        my_pinyin.put("keep_first_letter",true);
        my_pinyin.put("keep_separate_first_letter",true);
        my_pinyin.put("keep_full_pinyin",true);
        my_pinyin.put("keep_original",true);
        my_pinyin.put("limit_first_letter_length",16);
        my_pinyin.put("lowercase",true);
        //配置同义词filter
        Map filter=new HashMap();
        Map my_syno_filter=new HashMap();
        my_syno_filter.put("type","synonym");
        my_syno_filter.put("synonyms_path","analysis/synonym_community.txt");//同义词定义文件
        filter.put("my_syno_filter",my_syno_filter);
        analysis.put("filter",filter);
        //定义有同义词功能的分词器
        Map ik_syno_smart=new HashMap();
        ik_syno_smart.put("type","custom");
        ik_syno_smart.put("tokenizer","ik_smart");
        List<String> filterNames=new ArrayList<>();
        filterNames.add("my_syno_filter");
        ik_syno_smart.put("filter",filterNames);
        Map ik_syno_max=new HashMap();
        ik_syno_max.put("type","custom");
        ik_syno_max.put("tokenizer","ik_max_word");
        ik_syno_max.put("filter",filterNames);

        analyzer.put("ik_syno_smart",ik_syno_smart);
        analyzer.put("ik_syno_max",ik_syno_max);


        String analysisStr=JSON.toJSONString(analysis);
        log.info(">>>settting analysis:{}",analysisStr);

     /* Settings.Builder builder= Settings.builder().put("index.number_of_shards",1)
                .put("index.number_of_replicas",0).put("index.analysis",analysisStr);
       log.info(">>>>builder:{}",builder.toString());
       request.settings(builder);*/
        settingMap.put("analysis",analysis);
        settingMap.put("index.number_of_shards",1);
        settingMap.put("index.number_of_replicas",0);
        request.settings(settingMap);



    }

    /**
     * 判断配置项的索引是否存在，注意与isExistsIndex区别
     * @param idxName 索引名
     * @return
     */
    private boolean indexExist(String idxName) throws IOException {
        GetIndexRequest request=new GetIndexRequest(idxName);
        //TRUE-返回本地信息检索状态，FALSE-还是从主节点检索状态
        request.local(false);
        //是否适应被人可读的格式返回
        request.humanReadable(true);
        //是否为每个索引返回所有默认设置
        request.includeDefaults(false);
        //控制如何解决不可用的索引以及如何扩展通配符表达式,忽略不可用索引的索引选项，仅将通配符扩展为开放索引，并且不允许从通配符表达式解析任何索引
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 判断索引是否存在
     * @param idxName
     * @return
     * @throws IOException
     */
    public boolean isExitsIndex(String idxName) throws IOException {
        return restHighLevelClient.indices().exists(new GetIndexRequest(idxName),RequestOptions.DEFAULT);
    }

    /**
     * 插入/更新数据
     * @param idxName
     * @param entity 对象
     */
    public void insertOrUpdateOne(String idxName, ElasticEntity entity){
        IndexRequest request=new IndexRequest(idxName);
        log.info("Data:id={},entity={}",entity.getId(), JSON.toJSONString(entity.getData()));
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()),XContentType.JSON);
        try {
            restHighLevelClient.index(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文档
     * @param idxName 索引名
     * @param entity 对象
     */
    public void deleteOne(String idxName,ElasticEntity entity){
        DeleteRequest request= new DeleteRequest(idxName);
        request.id(entity.getId());
        try {
            restHighLevelClient.delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量插入
     * @param idxName
     * @param list
     */
    public void insertBatch(String idxName, List<ElasticEntity> list){
        BulkRequest request=new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(idxName).id(item.getId()).
                source(JSON.toJSONString(item.getData()),XContentType.JSON)));
        try {
            restHighLevelClient.bulk(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除
     * @param idxName 索引名称
     * @param idList 待删除id列表
     * @param <T>
     */
    public <T> void deleteBatch(String idxName, Collection<T> idList){
        BulkRequest request=new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName,item.toString())));
        try {
            restHighLevelClient.bulk(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 搜索
     * @param idxName
     * @param builder 查询参数
     * @param c 结果类对象
     * @param <T>
     * @return
     */
    public <T> List<T> search(String idxName, SearchSourceBuilder builder,Class<T> c){
        SearchRequest request=new SearchRequest(idxName);
        request.source(builder);
        try{
            SearchResponse response=restHighLevelClient.search(request,RequestOptions.DEFAULT);
            SearchHit[] hits=response.getHits().getHits();
            List<T> res=new ArrayList<>(hits.length);
            for(SearchHit hit:hits){
                res.add(JSON.parseObject(hit.getSourceAsString(),c));
            }
            return res;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除index
     * @param idxName
     */
    public void deleteIndex(String idxName){
       try{
           if(!this.indexExist(idxName)){
                log.error("idexName={}已经存在",idxName);
                return;
           }
           restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName),RequestOptions.DEFAULT);
       }catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
    public void deleteByQuery(String idxName, QueryBuilder builder){
        DeleteByQueryRequest request=new DeleteByQueryRequest(idxName);
        request.setQuery(builder);
        //设置批量炒作数量，做大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            restHighLevelClient.deleteByQuery(request,RequestOptions.DEFAULT);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
