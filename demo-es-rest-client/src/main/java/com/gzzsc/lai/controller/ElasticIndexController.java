package com.gzzsc.lai.controller;

import com.alibaba.fastjson.JSON;
import com.gzzsc.lai.service.BaseElasticService;
import com.gzzsc.lai.vo.IdxVo;
import com.gzzsc.lai.vo.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ElasticIndexController
 * @Deacription es索引操作controller
 * @Author laizs
 * @Date 2020/9/10 14:13
 **/
@RestController
@Slf4j
@RequestMapping("/elastic")
@Api(tags = "ES索引操作")
public class ElasticIndexController {
    @Autowired
    private BaseElasticService baseElasticService;
    @GetMapping("/")
    @ApiOperation("测试")
    public ResultResponse index(String index){
        return new ResultResponse();
    }
    @ApiOperation("创建elastic索引")
    @PostMapping("/createIndex")
    public ResultResponse createIndex(@RequestBody IdxVo idxVo){
        ResultResponse response=new ResultResponse();
        try {
            if(!baseElasticService.isExitsIndex(idxVo.getIdxName())){
                String idxSql= JSON.toJSONString(idxVo.getIdxSql());
                log.info("idxName={},idxSql={}",idxVo.getIdxName(),idxVo.getIdxSql());
                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("idxName={"+idxVo.getIdxName()+"}已经被存在，无法创建");
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg(e.getMessage());
        }
        return response;
    }
    @ApiOperation("判断索引是否存在")
    @GetMapping("/exist/{index}")
    public ResultResponse indexExist(@PathVariable(value = "index") String index){
        ResultResponse response=new ResultResponse();
        try {
            if(this.baseElasticService.isExitsIndex(index)){
                response.setStatus(true);
                response.setMsg("index:"+index+"存在");
            }else{
                response.setStatus(false);
                response.setMsg("index:"+index+"不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("调用es失败,e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("删除索引")
    @GetMapping("/del/{index}")
    public ResultResponse indexDel(@PathVariable String index){
        ResultResponse response=new ResultResponse();
        try{
            this.baseElasticService.deleteIndex(index);
            response.setStatus(true);
            response.setMsg("索引:"+index+"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("调用es失败,e:"+e.getMessage());
        }
        return response;
    }
}
