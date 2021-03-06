package com.woniuxy.controller;


import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.vo.UserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/accountLogin")
    public Result accountLogin(@RequestBody UserVO userVO){
        System.out.println(userVO.getUsername());
        System.out.println(userVO.getPassword());
        System.out.println(userVO.getChecked());


        return  new Result(true, StatusCode.OK,"登录成功",userVO);
    }

}

