package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.provider.entity.Employee;
import com.gzzsc.lai.mapper.EmployeeMapper;
import com.gzzsc.lai.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author laizs
 * @since 2020-04-27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    /**
     * 保存<>br</>
     * 事务
     *
     * @param e1
     * @param e2
     */
    @Override
    public void saveTwo(Employee e1, Employee e2) {
        this.save(e1);
        int i=1/0;
        this.save(e2);
    }
}
