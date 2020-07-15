package com.gzzsc.lai.service.impl;

import com.gzzsc.lai.entity.Doctor;
import com.gzzsc.lai.mapper.DoctorMapper;
import com.gzzsc.lai.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DoctorServiceImpl
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/2 15:44
 **/
@SuppressWarnings("ALL")
@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    /**
     * @param doctor
     */
    @Override
    public void save(Doctor doctor) {
        this.doctorMapper.insertSelective(doctor);
    }

    /**
     * @return
     */
    @Override
    public int deleteAll() {
        return this.doctorMapper.deleteByExample(null);
    }

    /**
     * @return
     */
    @Override
    public List<Doctor> findAll() {
       return this.doctorMapper.selectByExample(null);
    }
}
