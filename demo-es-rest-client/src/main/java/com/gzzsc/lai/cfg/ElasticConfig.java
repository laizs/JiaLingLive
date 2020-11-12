package com.gzzsc.lai.cfg;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ElasticConfig
 * @Deacription es配置类
 * @Author laizs
 * @Date 2020/9/9 17:06
 **/
@Configuration
@Slf4j
public class ElasticConfig {
    @Value("${es.host}")
    private String host;
    @Value("${es.port}")
    private int port;
    @Value("${es.scheme}")
    private String scheme;
    @Bean
    public RestClientBuilder restClientBuilder(){
        log.info("----------restHighLevelClient--------");
        return RestClient.builder(makeHttpHost());
    }
    @Bean RestClient elasticsearchRestClient(){
        return RestClient.builder(new HttpHost(host,port,scheme)).build();
    }
    private HttpHost makeHttpHost(){
        log.info("----------makeHttpHost----host-{}---",host);
        return new HttpHost(host,port,scheme);
    }
    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder){
        log.info("----------restHighLevelClient--------");
        return new RestHighLevelClient(restClientBuilder);
    }
}
