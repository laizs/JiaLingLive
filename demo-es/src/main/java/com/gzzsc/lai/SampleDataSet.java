package com.gzzsc.lai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.gzzsc.lai.es.entity.Constants;
import com.gzzsc.lai.es.entity.Department;
import com.gzzsc.lai.es.entity.Employee;
import com.gzzsc.lai.es.entity.Organization;
import com.gzzsc.lai.es.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName SampleDataSet
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/2 15:04
 **/
public class SampleDataSet {
    private final static Logger LOGGER= LoggerFactory.getLogger(SampleDataSet.class);
    @Autowired
    EmployeeRepository repository;
    @Autowired
    ElasticsearchTemplate template;
    @PostConstruct
    public void init(){
        for (int i=0;i<10;i++){
            bulk(i);
        }
    }
    private void bulk(int ii){
        try {
            if(!template.indexExists(Constants.EMPLOYEE_INDEX)){
                template.createIndex(Constants.EMPLOYEE_INDEX);
            }
            ObjectMapper mapper=new ObjectMapper();
            List<IndexQuery> queries=new ArrayList<>();
            List<Employee> employees=ranEmployees();
            for(Employee employee:employees){
                IndexQuery indexQuery=new IndexQuery();
                indexQuery.setId(employee.getId().toString());
                indexQuery.setSource(mapper.writeValueAsString(employee));
                //set the index name & doc type
                indexQuery.setIndexName(Constants.EMPLOYEE_INDEX);
                indexQuery.setType(Constants.EMPLOYEE_INDEX_TYPE);
                queries.add(indexQuery);
            }
            if(queries.size()>0){
                template.bulkIndex(queries);
            }
            template.refresh(Constants.EMPLOYEE_INDEX);
            LOGGER.info("Bulkindex completed:{}",ii);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error bulk index",e);
        }


    }
    private List<Employee> ranEmployees(){
        List<Employee> employees=new ArrayList<>();
        int id=(int)repository.count();
        LOGGER.info("starting from id:{}",id);
        for(int i=id;i<100+id;i++){
            Random r=new Random();
            Faker faker = new Faker();
            Employee employee=new Employee();
            employee.setId((long) i);
            employee.setName(faker.name().username());
            employee.setAge(r.nextInt(60));
            employee.setPosition(faker.job().position());
            int departmentId=r.nextInt(5000);
            employee.setDepartment(new Department((long)departmentId,faker.company().name()));
            int organizationId=departmentId%100;
            employee.setOrganization(new Organization((long)organizationId,"TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;
    }
}
