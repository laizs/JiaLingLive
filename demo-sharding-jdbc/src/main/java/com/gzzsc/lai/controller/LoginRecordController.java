package com.gzzsc.lai.controller;

import com.gzzsc.lai.entity.LoginRecord;
import com.gzzsc.lai.service.LoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName LoginRecordController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/6/30 17:00
 **/
@Api(tags="登录日志接口")
@RestController
@RequestMapping("/loginRecord")
public class LoginRecordController {
    @Autowired
    private LoginRecordService loginRecordService;
    @ApiOperation("查询所有登录日志")
    @GetMapping("")
    public List<LoginRecord> findAll(){
        return this.loginRecordService.findAll();
    }
    @PostMapping("")
    @ApiOperation("新增单条登录日志")
    public LoginRecord save(@RequestBody  LoginRecord loginRecord){
        loginRecord.setId(null);//id使用雪花算法生成
        this.loginRecordService.save(loginRecord);
        return loginRecord;
    }
    @ApiOperation("删除所有的登录日志")
    @DeleteMapping("")
    public int deleteAll(){
        return this.loginRecordService.deleteAll();
    }
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public LoginRecord getById(@PathVariable(name = "id") Long id){
        return this.loginRecordService.getById(id);
    }
    @ApiOperation("根据日期查找")
    @GetMapping("/findByDateStr")
    public List<LoginRecord> findByDateStr(@RequestParam(name="dateStr") String dateStr){
        return this.loginRecordService.findDateStr(dateStr);
    }
    @ApiOperation("根据日期删除")
    @DeleteMapping("/deleteByDateStr")
    public int deleteByDateStr(@RequestParam(name="dateStr") String dateStr){
        return this.loginRecordService.deleteByDateStr(dateStr);
    }
    @ApiOperation("根据日期修改账号")
    @PutMapping("/updateAccountByDateStr")
    public int updateAccountByDateStr(String userAccount,String dateStr){
        return this.loginRecordService.updateAccountByDateStr(userAccount,dateStr);
    }
    @ApiOperation("根据日期模糊查询")
    @GetMapping("/findByDateStrLike")
    public List<LoginRecord> findByDateStrLike(String dateStr){
        return this.loginRecordService.findByDateStrLike(dateStr);
    }
    @ApiOperation("根据日期范围查询")
    @GetMapping("/findByDateStrBetween")
    public List<LoginRecord> findByDateStrBetween(String dateStr1,String dateStr2){
        return this.loginRecordService.findByDateStrBetween(dateStr1,dateStr2);
    }

}
