package com.gzzsc.lai.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.gzzsc.lai.entity.User;
import com.gzzsc.lai.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserController
 * @Deacription 用户信息controller
 * @Author laizs
 * @Date 2020/5/26 15:16
 **/
@SuppressWarnings("ALL")
@RequestMapping("/user")
@RestController
@Api(tags = "用户信息接口")
public class UserController {
    private final static Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/{id}")
    @ApiImplicitParam(name="id",value = "用户id",defaultValue = "3",required = true)
    public User getUser(@PathVariable int id){
            logger.info("id:{}",id);
            return this.userService.getById(id);
    }
    @GetMapping("/saveAll")
    @ApiOperation("批量生成用户数据")
    public String saveAll(){
        for(int i=1;i<=20;i++){
            User u=new User();
           // u.setId(i);
            u.setUsername("tiger"+i);
            u.setPassword("123");
            this.userService.save(u);
        }
        return "success";
    }
    @GetMapping("/")
    @ApiOperation("查询所有用户数据")
    public List<User> getAll(){
        logger.info("get all---");
        return this.userService.getAll();
    }
    @PostMapping("/")
    @ApiOperation("添加单个用户数据")
    public User addUser(@RequestBody User user){
        logger.info("add user:{}", JSON.toJSONString(user));
        this.userService.save(user);
        return user;
    }
    @ApiOperation("搜索")
    @GetMapping("/search")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "1"),
                    @ApiImplicitParam(name="username",value = "姓名",defaultValue = "张三")
            }
    )
    public List<User> search(@RequestParam(name = "id",required = false) Integer id,
                             @RequestParam(name = "username",required = false) String username){
        logger.info("search,id:{},username:{}",id,username);
       return  this.userService.search(id,username);

    }

    /**
     * 分页搜索
     * @param id
     * @param username
     * @param page
     * @param pageSize
     * @return
     */
    @ApiOperation("分页搜索")
    @GetMapping("/searchPage")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "1"),
                    @ApiImplicitParam(name="username",value = "姓名",defaultValue = "张三"),
                    @ApiImplicitParam(name="page",value = "页码",defaultValue = "1"),
                    @ApiImplicitParam(name="pageSize",value = "页大小",defaultValue = "5")
            }
    )
    public Page<User> searchPage(@RequestParam(name = "id",required = false) Integer id,
                                 @RequestParam(name = "username",required = false) String username,
                                 @RequestParam(name = "page",required = false) Integer page,
                                 @RequestParam(name = "pageSize",required = false) Integer pageSize){
        logger.info("searchPage,id:{},username:{},page:{},pageSize:{}",id,username,page,pageSize);
        return this.userService.searchPage(id,username,page,pageSize);

    }

    /**
     * 删除所有
     * @return
     */
    @ApiOperation("删除所有用户数据")
    @DeleteMapping("/deleteAll")
    public int deleteAll(){
        return this.userService.deleteAll();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "1",required = true)
    public int deleteById(@PathVariable  Integer id){
        int r= this.userService.deleteById(id);
        int a=1/0;
        return r;
    }

    /**
     * 根据用户姓名删除
     * @return
     */
    @ApiOperation("根据用户姓名删除")
    @ApiImplicitParam(name="username",value = "用户姓名",defaultValue = "张三")
    @DeleteMapping("/deleteByUsername")
    public int deleteByUsername(@RequestParam String username){
        return this.userService.deleteByUsername(username);
    }

    /**
     * 更新单个用户信息
     * @param user
     * @return
     */
    @ApiOperation("更新单个用户信息")
    @PutMapping("/")
    public int update(@RequestBody  User user){
        return this.userService.update(user);
    }

    /**
     * 根据用户姓名更新密码
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("根据用户姓名更新密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "姓名",defaultValue = "张三",required = true),
            @ApiImplicitParam(name="password",value = "密码",defaultValue = "abc123",required =true )
    })
    @PutMapping("/updatePassByUserName")
    public int updatePassByUserName(@RequestParam String username,@RequestParam String password){
            return this.userService.updatePassByUserName(username,password);
    }
}
