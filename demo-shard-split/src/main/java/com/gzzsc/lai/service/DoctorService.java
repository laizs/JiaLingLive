package com.gzzsc.lai.service;

import com.gzzsc.lai.provider.entity.Doctor;

import java.util.List;

/**
 * @ClassName DoctorService
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/2 15:39
 **/
public interface DoctorService {
    /**
     *
     * @param doctor
     */
    void save(Doctor doctor);
    /**
     *
     * @return
     */
    int deleteAll();
    /**
     *
     * @return
     */
    List<Doctor> findAll();


}
