package com.gzzsc.lai.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzsc.lai.entity.Employee;
import com.gzzsc.lai.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author laizs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/")
    public List<Employee> list(){
       return  employeeService.list(null);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Employee get(@PathVariable(name = "id") int id){
        return this.employeeService.getById(id);
    }

    /**
     * 新增
     * @param employee
     * @return
     */
    @PostMapping("/")
    public Employee save(@RequestBody Employee employee){
            //this.employeeService.save(employee);
            this.employeeService.saveTwo(employee,employee);
            return employee;
    }

    /**
     * 更新
     * @param id
     * @param employee
     * @return
     */
    @PutMapping("/{id}")
    public Employee update(@PathVariable(name="id") Integer id,@RequestBody Employee employee){
        employee.setId(id);
        this.employeeService.updateById(employee);
        return employee;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable(name="id") Integer id) {
       boolean b= this.employeeService.removeById(id);
        return b;
    }
    /**
     * 分页
     */
    @GetMapping("/page/{p}")
    public IPage<Employee> page(@PathVariable  int p){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        //queryWrapper.like("lastName","a");
        IPage<Employee> page=new Page();
        page.setSize(2);
        page.setCurrent(p);
        return this.employeeService.page(page,queryWrapper);
    }

}

