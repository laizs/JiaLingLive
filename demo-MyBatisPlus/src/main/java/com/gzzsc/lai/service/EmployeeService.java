package com.gzzsc.lai.service;

import com.gzzsc.lai.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author laizs
 * @since 2020-04-27
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 保存<>br</>
     * 事务
     * @param e1
     * @param e2
     */
    @Transactional(rollbackFor = {Exception.class,Error.class})
    void saveTwo(Employee e1,Employee e2);
}
