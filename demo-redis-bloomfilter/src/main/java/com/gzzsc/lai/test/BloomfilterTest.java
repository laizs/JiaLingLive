package com.gzzsc.lai.test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @className BloomfilterTest
 * @Deacription 测试类
 * @Author laizs
 * @Date 2021/11/30 11:21
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class BloomfilterTest {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 测试使用redis的bitmap来实现布隆过滤器
     */
    @Test
    public void testBloomFilter(){
        RBloomFilter<String> bloomFilter= redissonClient.getBloomFilter("phoneList");
        bloomFilter.tryInit(10000l,0.03);//预计元素总数，误差率3%
        //布隆器添加数据
        for(int i=0;i<1000;i++){
            bloomFilter.add("135"+i);
        }
        System.out.println("135100是否存在:"+bloomFilter.contains("135100"));
        System.out.println("135100x是否存在:"+bloomFilter.contains("135100x"));
        System.out.println("预计插入数量：" + bloomFilter.getExpectedInsertions());
        System.out.println("容错率：" + bloomFilter.getFalseProbability());
        System.out.println("hash函数的个数：" + bloomFilter.getHashIterations());
        System.out.println("插入对象的个数：" + bloomFilter.count());
    }

    /**
     * 测试使用google的guava实现的布隆过滤器
     */
    @Test
    public void testGuavaBloomfilter(){
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),1000,0.01);
        //布隆器添加数据
        for(int i=0;i<1000;i++){
            bloomFilter.put("10086"+i);
        }
        System.out.println("10086100是否存在:"+bloomFilter.mightContain("10086100"));
        System.out.println("10086x是否存在:"+bloomFilter.mightContain("10086x"));


    }
}
