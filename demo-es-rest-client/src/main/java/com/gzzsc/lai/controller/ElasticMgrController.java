package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.Community;
import com.gzzsc.lai.entity.ElasticEntity;
import com.gzzsc.lai.mapper.CommunityMapper;
import com.gzzsc.lai.service.BaseElasticService;
import com.gzzsc.lai.utils.ElasticUtils;
import com.gzzsc.lai.vo.ElasticDataVo;
import com.gzzsc.lai.vo.QueryVo;
import com.gzzsc.lai.vo.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName ElasticMgrController
 * @Deacription es数据管理
 * @Author laizs
 * @Date 2020/9/11 14:43
 **/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@RequestMapping("/elasticMgr")
@RestController
@Api(tags = "es数据管理")
public class ElasticMgrController {
    @Autowired
    private BaseElasticService baseElasticService;
    @Autowired
    private CommunityMapper communityMapper;
    @PostMapping("/add")
    @ApiOperation("新增数据")
    public ResultResponse add(@RequestBody ElasticDataVo elasticDataVo){
        ResultResponse response=new ResultResponse();
        try {
            if(StringUtils.isEmpty(elasticDataVo.getIdxName())){
                response.setStatus(false);
                response.setMsg("索引为空，不允许提交");
                return response;
            }
            ElasticEntity elasticEntity=new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());
            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(),elasticEntity);
            response.setMsg("数据添加成功");
            response.setStatus(true);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("服务器异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("删除数据")
    @DeleteMapping(value = "/delete")
    public ResultResponse delete(@RequestBody ElasticDataVo elasticDataVo){
        ResultResponse response=new ResultResponse();
        try {
            if(StringUtils.isEmpty(elasticDataVo.getIdxName())){
                response.setStatus(false);
                response.setMsg("索引为空，不允许提交");
                return response;
            }
            this.baseElasticService.deleteOne(elasticDataVo.getIdxName(),elasticDataVo.getElasticEntity());
            response.setStatus(true);
            response.setMsg("删除数据成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("服务器异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("批量数据入库")
    @PutMapping("/bulk/{index}")
    public ResultResponse bulk(@PathVariable String index){
        ResultResponse response=new ResultResponse();
        try {
            if(!this.baseElasticService.isExitsIndex(index)){
                response.setStatus(false);
                response.setMsg("索引不存在，无法批量入库数据");
                return response;
            }
            //查询所有小区数据
            List<Community> communityList=this.communityMapper.selectByExample(null);
           for(Community c:communityList){
               List<ElasticEntity> elasticEntities=new ArrayList<>();
               ElasticEntity elasticEntity=new ElasticEntity();
               elasticEntity.setId(c.getId());
               Map data=new HashMap<>();
               data.put("localName",c.getAddress());
               elasticEntity.setData(data);
               elasticEntities.add(elasticEntity);
               this.baseElasticService.insertBatch(index,elasticEntities);
           }
           response.setStatus(true);
           response.setMsg("批量数据入库成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("服务器异常，e:"+e.getMessage());
        }
        return response;
    }
    @PostMapping("/search")
    @ApiOperation("搜索数据")
    public ResultResponse search(@RequestBody QueryVo queryVo) {
        ResultResponse response = new ResultResponse();
        if (StringUtils.isEmpty(queryVo.getIdxName())) {
            response.setStatus(true);
            response.setMsg("索引为空 无法搜索数据");
            return response;
        }
        try {
            Class<?> clazz= ElasticUtils.getClazz(queryVo.getClassName());
            Map<String,Object> params=queryVo.getQuery().get("match");
            Set<String> keys=params.keySet();
            MatchQueryBuilder queryBuilders=null;
            for(String ke:keys){
                queryBuilders= QueryBuilders.matchQuery(ke,params.get(ke));
            }
            if(null!=queryBuilders){
                SearchSourceBuilder searchSourceBuilder=ElasticUtils.initSearchBuilder(queryBuilders);
               List<?> data= baseElasticService.search(queryVo.getIdxName(),searchSourceBuilder,clazz);
                response.setData(data);
            }
            response.setStatus(true);
            response.setMsg("成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }

        return response;
    }
}
