package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.Community;
import com.gzzsc.lai.es.entity.CommunityEntity;
import com.gzzsc.lai.es.entity.Constants;
import com.gzzsc.lai.es.repository.CommunityRepository;
import com.gzzsc.lai.mapper.CommunityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CommunityController
 * @Deacription 小区
 * @Author laizs
 * @Date 2020/9/7 17:47
 **/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Api(tags = "小区数据服务")
@RequestMapping("/community")
@RestController
public class CommunityController {
    public static final Logger LOGGER= LoggerFactory.getLogger(CommunityController.class);
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private CommunityRepository communityRepository;
    @ApiOperation("完全重建索引数据")
    @GetMapping("/initIndexData")
    public String initIndexData(){
        //删除索引
        LOGGER.info("----删除索引--:{}",Constants.COMMUNITY_INDEX);
        this.elasticsearchTemplate.deleteIndex(Constants.COMMUNITY_INDEX);
        LOGGER.info("----创建索引--:{}",Constants.COMMUNITY_INDEX);
        //创建新索引
        this.elasticsearchTemplate.createIndex(CommunityEntity.class);
        LOGGER.info("----配置映射--");
        //配置字段映射
        this.elasticsearchTemplate.putMapping(CommunityEntity.class);
        //查询所有小区数据
        List<Community> communities=this.communityMapper.selectByExample(null);
        LOGGER.info("----小区数:{}",communities.size());
        long t1=System.currentTimeMillis();
        for(Community c:communities){
            CommunityEntity ce=new CommunityEntity();
            ce.setId(c.getId());
            ce.setName(c.getName());
            ce.setNamekeyword(c.getName());
            ce.setOrganizationSeq(c.getOrganizationSeq());
            ce.setAddress(c.getAddress());
            ce.setTerminalCount(c.getTerminalCount());
            communityRepository.save(ce);
        }
        long t2=System.currentTimeMillis();
        LOGGER.info("----导入{}条数据到es，耗时：{}",communities.size(),(t2-t1));
        return "success";
    }
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public List<CommunityEntity> findAll(){
        Iterable<CommunityEntity> i= this.communityRepository.findAll();
        List<CommunityEntity> list=new ArrayList<>();
        i.forEach(c->{
            list.add(c);
        });
        return list;
    }
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Page<CommunityEntity> page(@RequestParam int page, @RequestParam int pageSize){
        //排序
        Sort sort=Sort.by("terminalCount").descending();
        return this.communityRepository.findAll(PageRequest.of(page,pageSize,sort));
    }
    @ApiOperation("match query")
    @PostMapping("/matchQuery")
    public Page<CommunityEntity> matchQuery(@RequestParam  String keyword){
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("name",keyword));
        Page<CommunityEntity> page=this.communityRepository.search(queryBuilder.build());
        return page;
    }
    @ApiOperation("term query")
    @PostMapping("/termQuery")
    public Page<CommunityEntity> termQuery(@RequestParam String keyword){
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("namekeyword",keyword));
        Page<CommunityEntity> page=this.communityRepository.search(queryBuilder.build());
        return page;
    }
    @ApiOperation("fuzzy query")
    @PostMapping("/fuzzyQuery")
    public Page<CommunityEntity> fuzzyQuery(@RequestParam String keyword){
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("namekeyword",keyword));
        return this.communityRepository.search(queryBuilder.build());
    }
    @ApiOperation("bool query")
    @PostMapping("/boolQuery")
    public Page<CommunityEntity> boolQuery(@RequestParam String communityName,@RequestParam String address){
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
       /*queryBuilder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("namekeyword",communityName))
        .must(QueryBuilders.matchQuery("address",address)));*/
        queryBuilder.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.termQuery("namekeyword",communityName))
                .should(QueryBuilders.matchQuery("address",address)));
        return this.communityRepository.search(queryBuilder.build());
    }
    @ApiOperation("mutiMatch")
    @PostMapping("/mutiMatch")
    public Page<CommunityEntity> mutiMatch(@RequestParam String keyWord){
        NativeSearchQueryBuilder queryBuilder=new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyWord,"name","address"));
        return this.communityRepository.search(queryBuilder.build());
    }



}
