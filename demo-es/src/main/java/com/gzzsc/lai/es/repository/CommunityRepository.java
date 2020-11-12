package com.gzzsc.lai.es.repository;

import com.gzzsc.lai.es.entity.CommunityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CommunityRepository
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/7 22:56
 **/
@Repository
public interface CommunityRepository extends ElasticsearchRepository<CommunityEntity,String> {

}
