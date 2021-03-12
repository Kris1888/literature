package com.woniuxy.controller;


import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.model.Manager;
import com.woniuxy.service.ManagerService;
import com.woniuxy.vo.AllUserVo;
import com.woniuxy.vo.AuthorVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerService managerService;

    @GetMapping("/findUser")
    public Result findUser(){
        List<AllUserVo> allUser = managerService.findAllUser();
        Result result = new Result(true, StatusCode.OK,"查询用户成功",allUser);

    return result;
    }
    @RequestMapping("/findAuthor")
    public  Result findAuthor(){
        List<AuthorVO> allAuthor = managerService.findAllAuthor();
        Result result = new Result(true, StatusCode.OK, "查询作者成功",allAuthor);
        return result;

    }
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody Manager manager, HttpServletRequest request){
        //shiro认证
//        Subject subject = SecurityUtils.getSubject();
//         UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(manager.getManagerTel(), manager.getPassword());
//         subject.login(usernamePasswordToken);
//         return new Result(true,StatusCode.OK,"登陆成功",manager);
        System.out.println("进入登陆界面");
        System.out.println(manager);
        boolean login = managerService.login(manager);
        if (login){
            request.setAttribute("manager",manager);
            return new Result(true,StatusCode.OK,"登陆成功");
        }

       return  new Result(false,StatusCode.LOGINERROR,"登录失败");
    }

}

