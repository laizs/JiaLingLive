package com.gzzsc.lai.controller;

import com.gzzsc.lai.provider.entity.Doctor;
import com.gzzsc.lai.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DoctorController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/7/2 15:47
 **/
@Api(tags = "医生信息接口")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    /**
     *
     * @return
     */
    @ApiOperation("查询所有的医生")
    @GetMapping("")
    public List<Doctor> getAllDoctors(){
        return this.doctorService.findAll();
    }
    @ApiOperation("删除所有数据")
    @DeleteMapping("")
    public int deleteAll(){
        return this.doctorService.deleteAll();
    }
    @ApiOperation("批量新增医生数据")
    @PostMapping("/saveAll")
    public String saveAll(){
        for(int i=1;i<=10;i++){
            Doctor doctor=new Doctor();
            doctor.setName("d"+i);
            doctor.setAge(i);
            doctor.setCity("GuangZhou");
            this.doctorService.save(doctor);
        }
        return "success";
    }

}
