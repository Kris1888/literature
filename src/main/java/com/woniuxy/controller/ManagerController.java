package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.model.Application;
import com.woniuxy.model.Editor;
import com.woniuxy.model.Manager;
import com.woniuxy.model.Permission;
import com.woniuxy.service.ManagerService;
import com.woniuxy.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
//查询用户
    @RequestMapping("/findAllUser")
    @ResponseBody
    public Result findUser(){
        System.out.println("执行了这个方法");
        List<AllUserVo> allUser = managerService.findAllUser();
        Result result = new Result(true, StatusCode.OK,"查询用户成功",allUser);

        return result;
    }
    //查询作者
    @RequestMapping("/findAllAuthor")
    @ResponseBody

    public  Result findAuthor(){
        List<MAuthorVO> allAuthor = managerService.findAllAuthor();
        Result result = new Result(true, StatusCode.OK, "查询作者成功",allAuthor);
        return result;

    }
    //审核作者
    @RequestMapping("/checkAuthor")
    @ResponseBody
    public Result checkAuthor(String userId){
        boolean b = managerService.checkAuthor(userId);
        if (b){
            return new Result(true,StatusCode.OK,"审核作者成功");
        }
        return new Result(false,StatusCode.ERROR,"审核失败");
    }
    //签约作者
    @RequestMapping("/signAuthor")
    @ResponseBody
public Result signAuthor(String authorId){
    boolean b = managerService.sginAuthor(authorId);
    if (b){
        return new Result(true,StatusCode.OK,"签约作者成功");
    }
    return new Result(false,StatusCode.ERROR,"签约失败");
}
//禁用用户
    @RequestMapping("/banUser")
    @ResponseBody
public Result banUser(String userId){
    boolean b = managerService.banUser(userId);
    if (b){
        return new Result(true,StatusCode.OK,"禁用用户成功");

    }
    return new Result(false,StatusCode.ERROR,"禁用用户失败");
}
    //登陆
    @RequestMapping("/login")
    @ResponseBody
    public Result login( @RequestBody Manager manager, HttpServletRequest request){
        //shiro认证
//        Subject subject = SecurityUtils.getSubject();
//         UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(manager.getManagerTel(), manager.getPassword());
//         subject.login(usernamePasswordToken);
//         return new Result(true,StatusCode.OK,"登陆成功",manager);
        System.out.println("进入登陆界面");
        System.out.println(manager);
        boolean login = managerService.login(manager);
        if (login){
            Manager managerDb = managerService.getOne(new QueryWrapper<Manager>().eq("manager_tel", manager.getManagerTel()));
            request.setAttribute("manager",managerDb);
            return new Result(true,StatusCode.OK,"登陆成功",managerDb);
        }

       return  new Result(false,StatusCode.LOGINERROR,"登录失败");
    }
//查询所有作品
    @RequestMapping("/findAllBook")
    @ResponseBody
    public Result findAllBook(){
        List<MBookVo> list = managerService.findAllBook();
        return new Result(true,StatusCode.OK,"查询所有书籍成功",list);

    }
    //禁用作品
    @RequestMapping("/banBook")
    @ResponseBody
    public Result banBook(String bookId){
        boolean b = managerService.banBook(bookId);
        if (b){
            return new Result(true,StatusCode.OK,"禁用成功");
        }
        return new Result(false,StatusCode.ERROR,"禁用失败");
    }
    //查询所有编辑
    @RequestMapping("/findAllEditor")
    @ResponseBody
    public Result findAllEditor(){
        System.out.println("执行了这个查询编辑的方法");
        List<MEidtorVo> allEidtor = managerService.findAllEidtor();
        System.out.println(allEidtor);
        return new Result(true,StatusCode.OK,"查询编辑成功",allEidtor);
    }
    //添加编辑
    @RequestMapping("/addEditor")

    @ResponseBody
    public Result addEditor(@RequestBody Editor editor){
        System.out.println(editor);
        boolean b = managerService.addEditor(editor);
        if (b){
            return new Result(true,StatusCode.OK,"添加成功");
        }

        return new Result(false,StatusCode.ERROR,"添加失败");
    }
    //查询申请中心
    @RequestMapping("/getApplication")
    @ResponseBody
    public Result getApplication(){
        List<Application> list= managerService.getApplication();
        return new Result(true,StatusCode.OK,"查询成功",list);
    }
    //签约作品
    @RequestMapping("/signBook")
    @ResponseBody
    public Result signBook(ApplicationVo applicationVo){
        boolean b = managerService.signBook(applicationVo);
        if (b){
            return new Result(true,StatusCode.OK,"操作成功");
        }
        return new Result(false,StatusCode.ERROR,"操作失败");
    }
    //审核作品
    @RequestMapping("/checkBook")
    @ResponseBody
    public Result checkBook(ApplicationVo applicationVo){
        boolean b = managerService.checkBook(applicationVo);
        if (b){
            return new Result(true,StatusCode.OK,"操作成功");
        }
        return new Result(false,StatusCode.ERROR,"操作失败");
    }

    //查看合同
    @RequestMapping("/getContract")
    @ResponseBody
    public Result getContract(){
        List<ContractVo> contract = managerService.getContract();
        return new Result(true,StatusCode.OK,"查询成功",contract);
    }


    //获取权限菜单
    @RequestMapping("/getMenu")
    @ResponseBody
    public Result getMenu(String managerId){
        System.out.println(managerId);
        List<Permission> menu = managerService.getMenu(managerId);
        return new Result(true,StatusCode.OK, "欢迎使用系统",menu);
    }
}

