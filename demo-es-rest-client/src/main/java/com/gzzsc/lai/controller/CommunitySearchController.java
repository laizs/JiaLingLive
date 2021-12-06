package com.gzzsc.lai.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzzsc.lai.provider.entity.Community;
import com.gzzsc.lai.provider.entity.CommunityMapping;
import com.gzzsc.lai.provider.entity.ElasticEntity;
import com.gzzsc.lai.mapper.CommunityMapper;
import com.gzzsc.lai.service.BaseElasticService;
import com.gzzsc.lai.vo.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.StatsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortMode;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestion;
import org.elasticsearch.search.suggest.phrase.PhraseSuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CommunitySearchController
 * @Deacription 小区搜索服务
 * @Author laizs
 * @Date 2020/9/14 16:07
 **/
@SuppressWarnings("ALL")
@Api(tags = "小区搜索服务")
@RestController
@RequestMapping("/communitySearch")
@Slf4j
public class CommunitySearchController {
    private static String INDEXNAME="community";
    @Autowired
    private BaseElasticService baseElasticService;
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @ApiOperation("构建小区ES索引数据")
    @GetMapping("/buildIndex")
    public ResultResponse buidCommunityESIndex(){
        ResultResponse response=new ResultResponse();
        log.info("开始构建小区ES索引数据----index=?",INDEXNAME);
        try {
            if(this.baseElasticService.isExitsIndex(INDEXNAME)){
                log.warn("index=?已经存在，无法构建",INDEXNAME);
                response.setStatus(false);
                response.setMsg("index="+INDEXNAME+"已经存在，无法构建");
                return response;
            }
            String idxSql= JSON.toJSONString(CommunityMapping.build());
            log.info("构建小区ES索引,indexName:{},idxSql:",INDEXNAME);
            log.info(idxSql);
            this.baseElasticService.createIndex(INDEXNAME,idxSql);
            //导入数据
            List<Community> communityList=this.communityMapper.selectByExample(null);
            log.info("构建小区ES索引,小区数量:{}",communityList.size());
            List<ElasticEntity> elasticEntities=new ArrayList<>();
            if(null!=communityList){
                for(int i=0;i<500;i++ ){
                    Community c=communityList.get(i);
                    ElasticEntity e=new ElasticEntity();
                    e.setId(c.getId());
                    Map data=new HashMap<>();
                    data.put("name",c.getName());
                    data.put("nameKey",c.getName());
                    data.put("nameCompletion",c.getName());
                    data.put("namePinyin",c.getName());
                    data.put("address",c.getAddress());
                    data.put("enablePromotionActive",c.getEnablePromotionActive());
                    data.put("terminalCount",c.getTerminalCount());
                    e.setData(data);
                    elasticEntities.add(e);
                }
            }
            if(elasticEntities.size()>0){
                this.baseElasticService.insertBatch(INDEXNAME,elasticEntities);
            }
            log.info("构建小区ES索引,索引数据构建成功，个数：{}",elasticEntities.size());
            response.setStatus(true);
            response.setMsg("成功");

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("构建小区ES索引数据抛异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("rangeQuery搜索")
    @GetMapping("/rangeQuery")
    public ResultResponse rangeQuery(@RequestParam Integer  min,@RequestParam Integer max){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery("terminalCount").from(min).to(max);
        //排序
        FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        sourceBuilder.query(rangeQueryBuilder).sort(sortBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;

    }
    @ApiOperation("termQuery搜索")
    @PostMapping("/termQuery")
    public ResultResponse termQuery(@RequestParam String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder=QueryBuilders.termQuery("nameKey",keyword);
        //排序
        FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        sourceBuilder.query(termQueryBuilder).sort(sortBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("prefixQuery搜索")
    @PostMapping("/prefixQuery")
    public ResultResponse prefixQuery(@RequestParam String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        PrefixQueryBuilder prefixQueryBuilder=QueryBuilders.prefixQuery("nameKey",keyword);
        //排序
        FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        sourceBuilder.query(prefixQueryBuilder).sort(sortBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("wildcardQuery搜索")
    @PostMapping("/wildcardQuery")
    public ResultResponse wildcardQuery(@RequestParam  String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        WildcardQueryBuilder wildcardQueryBuilder=QueryBuilders.wildcardQuery("nameKey","*"+keyword+"*");
        //排序
        FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        sourceBuilder.query(wildcardQueryBuilder).sort(sortBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("multiMatchQuery搜索")
    @PostMapping("/multiMatchQuery")
    public ResultResponse multiMatchQuery(@RequestParam String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        String[] fieldNames={"name","address"};
        MultiMatchQueryBuilder multiMatchQueryBuilder=QueryBuilders.multiMatchQuery(keyword,fieldNames);
        //排序
       // FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        // sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        sourceBuilder.query(multiMatchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }

    @ApiOperation("bool搜索")
    @GetMapping("/boolSearch")
    public ResultResponse boolSearch(@RequestParam Integer  min,@RequestParam Integer max,@RequestParam  String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        //rangeQuery
        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery("terminalCount").from(min).to(max);
        //multiMatch
        String[] fieldNames={"name","address"};
        MultiMatchQueryBuilder multiMatchQueryBuilder=QueryBuilders.multiMatchQuery(keyword,fieldNames);
        //排序
        // FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        // sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.must(rangeQueryBuilder).must(multiMatchQueryBuilder);
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("高亮搜索")
    @GetMapping("/highlightSearch")
    public ResultResponse highlightSearch(@RequestParam Integer  min,@RequestParam Integer max,@RequestParam  String keyword){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        //rangeQuery
        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery("terminalCount").from(min).to(max);
        //multiMatch
        String[] fieldNames={"name","address"};
        MultiMatchQueryBuilder multiMatchQueryBuilder=QueryBuilders.multiMatchQuery(keyword,fieldNames);
        //排序
        // FieldSortBuilder sortBuilder= SortBuilders.fieldSort("terminalCount");
        // sortBuilder.sortMode(SortMode.MIN);//从小到大排序
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.must(rangeQueryBuilder).must(multiMatchQueryBuilder);
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //高亮显示
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<span class=\"red\">");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("name").field("address");
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response1=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits=response1.getHits();
            JSONArray jsonArray=new JSONArray();
            for(SearchHit hit:hits){
                String sourceAsString=hit.getSourceAsString();
                JSONObject jsonObject=JSONObject.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
                //高亮显示字段
                log.info("---highlight:{}",JSON.toJSONString(hit.getHighlightFields()));
                Map<String, HighlightField> highlightFieldMap=hit.getHighlightFields();
                HighlightField nameHighlightField =highlightFieldMap.get("name");
                HighlightField addressHighlightField= highlightFieldMap.get("address");
                log.info("---name highlight:{}",nameHighlightField);
                log.info("---address highlight:{}",addressHighlightField);
                Map highlightMap=new TreeMap();
                if(null!=nameHighlightField){
                    Text[] fragments = nameHighlightField.fragments();
                    String fragmentString = fragments[0].string();
                    highlightMap.put("name",fragmentString);
                }if(null!=addressHighlightField){
                    Text[] fragments = addressHighlightField.fragments();
                    String fragmentString = fragments[0].string();
                    highlightMap.put("address",fragmentString);
                }
                jsonObject.put("highlight",highlightMap);


            }
            response.setMsg("成功");
            response.setStatus(true);
            response.setData(jsonArray);
            response.setTotal(hits.getTotalHits().value);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("聚合查询接口")
    @GetMapping("/query/agg")
    @ApiImplicitParam(name = "opt",value = "操作：1 按terminalCount直接分桶 2 按terminalCount范围分桶",defaultValue = "1",required = true)
    public ResultResponse aggQuery( @RequestParam int opt){
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(INDEXNAME);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            if(1==opt){//按terminalCount直接分桶
                TermsAggregationBuilder termsAggregationBuilder= AggregationBuilders.terms("by_terminal_count").field("terminalCount");
                sourceBuilder.aggregation(termsAggregationBuilder);
                SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                Aggregations aggregations=searchResponse.getAggregations();
                log.info("-----aggs:{}",JSON.toJSONString(aggregations));
                Map<String, Aggregation> stringAggregationMap= aggregations.asMap();
                ParsedLongTerms parsedLongTerms= (ParsedLongTerms) stringAggregationMap.get("by_terminal_count");
                List<? extends Terms.Bucket> buckets=parsedLongTerms.getBuckets();
                Map<Integer,Long> map=new HashMap<>();
                for(Terms.Bucket bucket:buckets){
                    long docCount=bucket.getDocCount();//个数
                    Number keyAsNumber= bucket.getKeyAsNumber();//key为设备数
                    map.put(keyAsNumber.intValue(),docCount);
                }


                List<Map> data=new ArrayList<>();
                data.add(map);
                response.setData(data);
                response.setTotal((long) map.size());
            }else if(2==opt){//按terminalCount范围分桶
                RangeAggregationBuilder rangeAggregationBuilder=AggregationBuilders.range("range_terminal_count").field("terminalCount");
                rangeAggregationBuilder.addUnboundedTo("<50",50);
                rangeAggregationBuilder.addRange("50-100",50,100);
                rangeAggregationBuilder.addUnboundedFrom(">100",100);
                sourceBuilder.aggregation(rangeAggregationBuilder);
                SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                Aggregations aggregations=searchResponse.getAggregations();
                ParsedRange parsedRange=aggregations.get("range_terminal_count");
                Map map=new HashMap();
                for(Range.Bucket bucket:parsedRange.getBuckets()){
                    log.info("---key:{},docCount:{}",bucket.getKeyAsString(),bucket.getDocCount());
                    map.put(bucket.getKeyAsString(),bucket.getDocCount());
                }
                List<Map> data=new ArrayList<>();
                data.add(map);
                response.setData(data);


            }
            response.setMsg("成功");
            response.setStatus(true);

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("航班信息聚合查询demo")
    @GetMapping("/getFlightsAgg")
    public ResultResponse getFlightsAgg(){
        String idxName="kibana_sample_data_flights";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            TermsAggregationBuilder flight_dest_term= AggregationBuilders.terms("flight_dest").field("DestCountry").size(Integer.MAX_VALUE);
            //stats
            StatsAggregationBuilder flight_dest_stats= AggregationBuilders.stats("flight_dest_stats").field("AvgTicketPrice");
            //再对天气进行分类
            TermsAggregationBuilder weather_term= AggregationBuilders.terms("weather").field("DestWeather").size(Integer.MAX_VALUE);
            StatsAggregationBuilder weather_stats=AggregationBuilders.stats("weather_stats").field("AvgTicketPrice");
            flight_dest_term.subAggregation(flight_dest_stats);
            flight_dest_term.subAggregation(weather_term);
            weather_term.subAggregation(weather_stats);


            sourceBuilder.aggregation(flight_dest_term);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations=searchResponse.getAggregations();

            Terms terms=aggregations.get("flight_dest");
            List<Map> data=new ArrayList<>();
            for(Terms.Bucket bucket:terms.getBuckets()){
                Map dataMap=new HashMap();
                long docCount=bucket.getDocCount();//个数
                String key= bucket.getKeyAsString();//key
                Map ss=new HashMap();
                ss.put("docCount",docCount);
                Aggregations subAggregations=bucket.getAggregations();
                //统计数据stats
                ParsedStats parsedStats=subAggregations.get("flight_dest_stats");
                log.info("----avg:{}",parsedStats.getAvg());
                ss.put("avg",parsedStats.getAvg());
                ss.put("min",parsedStats.getMin());
                ss.put("max",parsedStats.getMax());
                ss.put("sum",parsedStats.getSum());
                dataMap.put(key,ss);
                //weather分组的数据
                List<Map> weatherList=new ArrayList<>();
                ss.put("weathers",weatherList);
                Terms weatherTerms=subAggregations.get("weather");
                for(Terms.Bucket weathBucket:weatherTerms.getBuckets()){
                    log.info("---weather:{}",weathBucket.getKeyAsString());
                    //weather下的stats
                    Aggregations weatherAggs=weathBucket.getAggregations();
                    ParsedStats weatherStats=weatherAggs.get("weather_stats");
                    Map weatherMap=new HashMap();
                    weatherMap.put("key",weathBucket.getKeyAsString());
                    weatherMap.put("docCount",weathBucket.getDocCount());
                    weatherMap.put("avg",weatherStats.getAvg());
                    weatherMap.put("min",weatherStats.getMin());
                    weatherMap.put("max",weatherStats.getMax());
                    weatherMap.put("sum",weatherStats.getSum());
                    weatherList.add(weatherMap);

                }
                data.add(dataMap);

            }


            response.setData(data);
            response.setTotal((long) data.size());
            response.setMsg("成功");
            response.setStatus(true);

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;

    }
    @ApiOperation("/suggester term推荐器")
    @GetMapping("/termSuggest")
    public ResultResponse termSuggest(@RequestParam String text){
        String idxName="blogs";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            TermSuggestionBuilder termSuggestionBuilder= SuggestBuilders.termSuggestion("body").text(text).
                    suggestMode(TermSuggestionBuilder.SuggestMode.MISSING);
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("name_suggest",termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                TermSuggestion termSuggestion= suggest.getSuggestion("name_suggest");
                List<Map> data=new ArrayList<>();
                for(TermSuggestion.Entry entry:termSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getText().string());
                    }
                    entryMap.put(entry.getText().string(),optionList);
                    data.add(entryMap);
                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("/suggester phrase推荐器")
    @GetMapping("/phraseSuggest")
    public ResultResponse phraseSuggest(@RequestParam String text){
        String idxName="blogs";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            PhraseSuggestionBuilder phraseSuggestionBuilder=SuggestBuilders.phraseSuggestion("body").text(text)
                    .highlight("<em>","</em>");//phrase suggestion
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("name_suggest",phraseSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                PhraseSuggestion phraseSuggestion= suggest.getSuggestion("name_suggest");
                List<Map> data=new ArrayList<>();
                for(PhraseSuggestion.Entry entry:phraseSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getHighlighted().toString()+":"+option.getScore());
                    }
                    entryMap.put(entry.getText().string(),optionList);
                    data.add(entryMap);
                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("/suggester completion推荐器")
    @GetMapping("/completionSuggest")
    public ResultResponse completionSuggest(@RequestParam String text){
        String idxName="blogs_completion";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            CompletionSuggestionBuilder completionSuggestionBuilder=SuggestBuilders.completionSuggestion("body").prefix(text);
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("my_suggest",completionSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                CompletionSuggestion completionSuggestion= suggest.getSuggestion("my_suggest");
                List<Map> data=new ArrayList<>();
                for(CompletionSuggestion.Entry entry:completionSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getText().toString()+":"+option.getScore());
                    }
                    entryMap.put(entry.getText().string(),optionList);
                    data.add(entryMap);
                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("/小区数据term suggest")
    @GetMapping("/communityTermSuggest")
    public ResultResponse communityTermSuggest(@RequestParam String name){
        String idxName="community";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            TermSuggestionBuilder termSuggestionBuilder= SuggestBuilders.termSuggestion("name").text(name).
                    suggestMode(TermSuggestionBuilder.SuggestMode.MISSING);
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("name_suggest",termSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                TermSuggestion termSuggestion= suggest.getSuggestion("name_suggest");
                List<Map> data=new ArrayList<>();
                for(TermSuggestion.Entry entry:termSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getText().string());
                    }
                    entryMap.put(entry.getText().string(),optionList);
                    data.add(entryMap);
                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("/小区数据 phrase推荐器")
    @GetMapping("/communityPhraseSuggest")
    public ResultResponse communityPhraseSuggest(@RequestParam String name){
        String idxName="community";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            PhraseSuggestionBuilder phraseSuggestionBuilder=SuggestBuilders.phraseSuggestion("name").text(name)
                    .highlight("<em>","</em>");//phrase suggestion
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("name_suggest",phraseSuggestionBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                PhraseSuggestion phraseSuggestion= suggest.getSuggestion("name_suggest");
                List<Map> data=new ArrayList<>();
                for(PhraseSuggestion.Entry entry:phraseSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getHighlighted().toString()+":"+option.getScore());
                    }
                    entryMap.put(entry.getText().string(),optionList);
                    data.add(entryMap);
                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
    @ApiOperation("/小区数据 completion推荐器")
    @GetMapping("/communityCompletionSuggest")
    public ResultResponse communityCompletionSuggest(@RequestParam String name){
        String idxName="community";
        ResultResponse response=new ResultResponse();
        SearchRequest searchRequest=new SearchRequest(idxName);
        SearchSourceBuilder  sourceBuilder=new SearchSourceBuilder();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.size(0);
        searchRequest.source(sourceBuilder);
        try {
            CompletionSuggestionBuilder completionSuggestionBuilder=SuggestBuilders.completionSuggestion("nameCompletion").prefix(name);
            SuggestBuilder suggestBuilder=new SuggestBuilder();
            suggestBuilder.addSuggestion("name_suggest",completionSuggestionBuilder);
            CompletionSuggestionBuilder pinyinSuggestBuilder=SuggestBuilders.completionSuggestion("namePinyin").prefix(name);
            suggestBuilder.addSuggestion("pinyin_suggest",pinyinSuggestBuilder);
            sourceBuilder.suggest(suggestBuilder);
            SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if(searchResponse.status().equals(RestStatus.OK)){
                //获取建议结果
                Suggest suggest= searchResponse.getSuggest();
                CompletionSuggestion completionSuggestion= suggest.getSuggestion("name_suggest");
                CompletionSuggestion pinyinComplectionSuggestion=suggest.getSuggestion("pinyin_suggest");
                List<Map> data=new ArrayList<>();
                for(CompletionSuggestion.Entry entry:completionSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getText().toString()+":"+option.getScore());
                    }
                    if(optionList.size()>0){
                        entryMap.put(entry.getText().string(),optionList);
                        data.add(entryMap);
                    }
                }
                for(CompletionSuggestion.Entry entry:pinyinComplectionSuggestion.getEntries()){
                    Map entryMap=new HashMap();
                    log.info("entry:{}",entry.getText());
                    List<String> optionList=new ArrayList<>();
                    for(Suggest.Suggestion.Entry.Option option: entry.getOptions()){
                        log.info("option:{},score:{}",option.getText(),option.getScore());
                        optionList.add(option.getText().toString()+":"+option.getScore());
                    }
                    if(optionList.size()>0){
                        entryMap.put(entry.getText().string(),optionList);
                        data.add(entryMap);
                    }

                }
                response.setData(data);
                response.setTotal((long) data.size());
                response.setMsg("成功");
                response.setStatus(true);
            }else{
                response.setMsg("搜索结果错误，status:"+searchResponse.status().getStatus());
                response.setStatus(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(false);
            response.setMsg("搜索异常，e:"+e.getMessage());
        }
        return response;
    }
}
