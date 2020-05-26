package com.gzzsc.lai.controller;

import com.gzzsc.lai.api.ApiReq;
import com.gzzsc.lai.api.ApiRsp;
import com.gzzsc.lai.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Deacription TODO
 * @Author laizs
 * @Date 2020/5/21 13:21
 **/
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    /**
     * 添加用户接口
     * @param userName
     * @param address
     * @return
     */
    @PostMapping("/")
    @ApiOperation("添加用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "姓名",defaultValue = "李四"),
            @ApiImplicitParam(name="address",value = "地址",defaultValue = "广州",required = true)
    })
    public ApiRsp addUser(String userName,@RequestParam(required = true)  String address){
        LOGGER.info("--添加用户接口---userName:{},address:{}",userName,address);
        ApiRsp rsp=new ApiRsp();
        rsp.setMsg("success");
        rsp.setStatus(0);
        return rsp;
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name="id",value ="用户id",defaultValue = "1",required = true)
    public ApiRsp<User> getUserById(@PathVariable Integer id){
        User user=new User();
        user.setId(id);
        user.setUserName("u"+id);
        user.setAddress("addr"+id);
        ApiRsp<User> rsp=new ApiRsp<>();
        rsp.setStatus(0);
        rsp.setMsg("success");
        rsp.setBody(user);
        return rsp;
    }
    @ApiOperation("根据用户id更新用户信息")
    @PutMapping("/{id}")
    @ApiImplicitParam(name="id",value="用户id",defaultValue ="2",required = true)
    public ApiRsp<User> updateUserById(@PathVariable Integer id, @RequestBody User user){
        user.setId(id);
        ApiRsp<User> rsp=new ApiRsp<>();
        rsp.setStatus(0);
        rsp.setMsg("success");
        rsp.setBody(user);
        return rsp;
    }
    @ApiOperation("查询用户列表")
    @PostMapping("/list")
    public ApiRsp<List<User>> getList(@RequestBody ApiReq req){
        ApiRsp<List<User>> rsp=new ApiRsp<>();
        rsp.setStatus(0);
        rsp.setMsg("success");
        List<User> userList=new ArrayList<>();
        User user1=new User();
        user1.setId(1);
        user1.setUserName("张三");
        user1.setAddress("广州");
        User user2=new User();
        user2.setId(2);
        user2.setUserName("李四");
        user2.setAddress("深圳");
        userList.add(user1);
        userList.add(user2);
        rsp.setBody(userList);
        return rsp;
    }
}
