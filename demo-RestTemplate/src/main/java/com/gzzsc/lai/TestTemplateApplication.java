package com.gzzsc.lai;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName TestTemplateApplication
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/17 14:54
 **/
@SpringBootApplication
public class TestTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestTemplateApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(10 * 1000);
        clientHttpRequestFactory.setReadTimeout(2 * 1000);
        return new RestTemplate(clientHttpRequestFactory);
    }
    @Bean(name = "poolingRestTemplate")
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate=builder.build();//生成一个RestTemplate实例
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * 客户端请求链接策略
     * @return
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        clientHttpRequestFactory.setConnectionRequestTimeout(1000);//设置连接超时  毫秒
        clientHttpRequestFactory.setReadTimeout(6000);//设置读写超时 毫秒
        clientHttpRequestFactory.setConnectionRequestTimeout(1000);//请求超时 毫秒
        return  clientHttpRequestFactory;
    }

    /**
     * 设置HTTP连接管理器，连接池相关配置管理
     * @return
     */
    @Bean
    public HttpClientBuilder httpClientBuilder(){
        HttpClientBuilder httpClientBuilder=HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        return httpClientBuilder;
    }

    /**
     * 链接连接池管理，可以keep-alive不断开连接请求，这样速度会更快
     * maxtotal 连接池最大连接数
     * defaultMaxPerRoute 每个主机的并发
     * ValidteAfterInactivity 可用空闲连接过期时间，重用空闲连接时先检查
     * 是否空闲时间超过这个时间，如果超过，释放socket重新建立
     * @return
     */
    @Bean
    public HttpClientConnectionManager poolingConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager=new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(100);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(500);
        poolingHttpClientConnectionManager.setValidateAfterInactivity(3000);
        return poolingHttpClientConnectionManager;
    }
}
