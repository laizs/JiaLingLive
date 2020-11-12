package com.gzzsc.lai.es.repository;

import com.gzzsc.lai.es.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName EmployeeRepository
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/2 14:53
 **/
@Repository
public interface EmployeeRepository extends ElasticsearchCrudRepository<Employee,Long> {
    List<Employee> findByOrganizationName(String name);
    List<Employee> findByName(String name);
}
