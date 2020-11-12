package com.gzzsc.lai.controller;

import com.gzzsc.lai.es.entity.Employee;
import com.gzzsc.lai.es.repository.EmployeeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName EmployeeController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/9/4 17:41
 **/
@RestController
@Api(tags = "员工搜索")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @PostMapping
    @ApiOperation(value="新增员工")
    public Employee add(@RequestBody Employee employee){
           return this.employeeRepository.save(employee);
    }
    @GetMapping("/{name}")
    @ApiOperation(value="根据姓名查找员工")
    public List<Employee> findByName(@PathVariable("name") String name){
        return this.employeeRepository.findByName(name);
    }
    @ApiOperation(value="根据公司名称查找员工")
    @GetMapping("/organization/{organizationName}")
    public List<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return employeeRepository.findByOrganizationName(organizationName);
    }
}
