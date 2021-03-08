package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.mapper.EditorMapper;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.User;
import com.woniuxy.service.EditorService;
import com.woniuxy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/editor")
public class EditorController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private EditorMapper editorMapper;
    @Resource
    private EditorService editorService;

    @Resource
    private BookMapper bookMapper;
    //管登录
    @PostMapping("login")
    public String login(User user, HttpSession session){
        System.out.println("前端传过来的user"+user);
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",user.getUserName());
        User user1 = userMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(user1)) {
            if(user.getPassword().equals(user1.getPassword())){
                session.setAttribute("user",user1);
                return "index";
             }else {
                return "login";
             }
        }else {
            return  "login";
        }
    }
//查阅所有作品
       @GetMapping("findallBook")
       public Result findallbook(Book book){
        System.out.println("进来了book");
         List<Book> books = editorService.findallBook();
        return  new Result(true, StatusCode.OK,"查阅所有作品成功",books);
      }


//查询作者
      @PostMapping("findallEditor")
       public  Result findallEditor(User user){
          System.out.println("进来查询作者了");
          List<User> users = editorService.findallEditor();
          return new Result(true,StatusCode.OK,"查询作者成功",users);
      }



//查询签约作品



    //查看作品的数据'
    @PostMapping("findallBookdata")
    public Result findallBookdata(){
        String s = editorService.findallBookdata();
        return  new Result(true,StatusCode.OK,"查看作品的数据",s);
    }



//    禁书
    @PostMapping("findupdatbook")
    public Result findupdatbook(){
        String findupdatbook = editorService.findupdatbook();
        return new Result(true,StatusCode.OK,"修改状态成功，禁书成功",findupdatbook);
    }


//    作品审核
      @PostMapping("insertAuthor")
      public Result insertAuthor(){


        return  new Result(true,StatusCode.OK,"作品审核成功");
    }
    

}

