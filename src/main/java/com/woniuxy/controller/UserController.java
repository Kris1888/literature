package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.model.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //用户注册
    @PostMapping("register")
    public Result register(@RequestBody User user){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUserName());
        User userDB=userService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(userDB)){
            String salt = SaltUtils.getSalt(8);
            Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 2048);
            // 保存加密的密码
            user.setUserName(user.getUserName());
            user.setPassword(md5Hash.toHex());
            user.setSalt(salt);
            //        存到数据中
            userService.save(user);
            return  new Result(true, StatusCode.OK,"注册成功");
        }else {
            return  new Result(true,StatusCode.OK,"已经被注册");
        }
    }
//    @PostMapping("login")
//    public Result login(@RequestBody UserVo userVo) {
////        System.out.println(userVo+"userV");
////        System.out.println(userVo+"userV");
////        Subject subject = SecurityUtils.getSubject();
////        UsernamePasswordToken token=new UsernamePasswordToken(userVo.getUsername(),userVo.getPassword());
////        subject.login(token);
//
//        return  new Result(true,StatusCode.OK,"登录成功",userVo);
//}


//    @RequestMapping("/bookcheck")
//    public Result bookcheck(){
//
//        return new Result();
//    }
}

