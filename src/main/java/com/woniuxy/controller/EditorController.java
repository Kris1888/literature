package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.mapper.EditorMapper;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.BookData;
import com.woniuxy.model.Editor;
import com.woniuxy.model.User;
import com.woniuxy.realm.CustomerRealm;
import com.woniuxy.service.EditorService;
import com.woniuxy.service.UserService;
import com.woniuxy.vo.EAuthorVo;
import com.woniuxy.vo.PageVO;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
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

    @Resource
    private CustomerRealm customerRealm;

//         @PostMapping("/register")
//     public Result regiseter(@RequestBody UserVO userVO){
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("tel",userVO.getTel());
//        userService.getOne(queryWrapper);
//
//        if(ObjectUtils)
//        String salt = SaltUtils.getSalt(8);
//        Md5Hash md5Hash=new Md5Hash(user.getPassword(),salt,1024);
//        //保村加密后的密码
//        user.setPassword(md5Hash.toHex());
//        user.setSalt(salt);
////        村user持久化数据库
//        userService.save(user);
//
//        return  new Result(true,StatusCode.OK,"注册成功",queryWrapper);
//    }
//    @PostMapping("/login")
//    @ResponseBody
//    public  Result  login(User user){
//        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
//
//        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
//
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1024);
//        customerRealm.setCredentialsMatcher(matcher);
//        defaultSecurityManager.setRealm(customerRealm);
//        SecurityUtils.setSecurityManager(defaultSecurityManager);
//        Subject subject= SecurityUtils.getSubject();
//        subject.login(new UsernamePasswordToken(user.getUserName(),user.getPassword()));
//        if(subject.isAuthenticated()){
//            return new Result(true,StatusCode.OK,"登录成功",subject);
//        }else {
//            return new Result(false,StatusCode.ERROR,"密码错误",subject);
//        }
//}

    //登录
    @PostMapping("login")
    public Result login(@RequestBody Editor editor, ServletRequest request) {
        System.out.println("前端传过来的user" + editor);
//    根据条件去查
        QueryWrapper<Editor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel", editor.getTel());
        Editor user1 = editorMapper.selectOne(queryWrapper);
//    判断
        if (!ObjectUtils.isEmpty(user1)) {
            if (editor.getPassword().equals(user1.getPassword())) {
                request.setAttribute("user", user1);
                System.out.println("登录成功");
                return new Result(true, StatusCode.OK, "登录成功", user1);
            } else {
                return new Result(true, StatusCode.OK, "账号或密码错误", user1);
            }
        }
        return new Result(true, StatusCode.OK, "账号或密码错误", user1);
    }







    //分页查询
    @GetMapping("find2Page")
    public Result find2Page(PageVO pageVO) {
        Page<Book> page = new Page<>(1, 3);
        IPage<Book> page1 = bookMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);//获取查询到的记录
        System.out.println(page.getTotal());//获取总记录数
        System.out.println(page.getCurrent());//获取当前页码
        System.out.println(page.getSize());//获取每页记录数
        return new Result(true, StatusCode.OK, "分页查询", page1);
    }
    //查阅所有作品
    @GetMapping("findallBook")
    public Result findallbook(Book book) {
        System.out.println("进来了book");
        List<Book> books = editorService.findallBook();
        return new Result(true, StatusCode.OK, "查阅所有作品成功", books);
    }

    //查询作者
    @PostMapping("findallEditor")
    public Result findallEditor(User user) {
        System.out.println("进来查询作者了");
        List<User> users = editorService.findallEditor();
        return new Result(true, StatusCode.OK, "查询作者成功", users);

    }

    //查询签约作品
    @PostMapping("findBook")
    public Result findBook() {
        System.out.println("进来签约作品");
        List<Book> book = editorService.findBook();
        return new Result(true, StatusCode.OK, "查询签约作品", book);

    }

    //查看作者的数据'
    @GetMapping("findallBookdata")
    public Result findallBookdata() {
        System.out.println("进来查看作品的数据");
        List<BookData> bookData = editorService.findallBookdata();
        return new Result(true, StatusCode.OK, "查看作品的数据", bookData);
    }

    //    查询指定作者的信息
    @GetMapping("findAutor")
    public Result findAutor(String authorId, HttpSession HttpSession) {
        Integer attribute = (Integer) HttpSession.getAttribute(authorId);
        System.out.println("前端传过来的"+attribute);
        List<EAuthorVo> bookDataStatus = editorService.findBookDataStatus();
        return new Result(true, StatusCode.OK, "查询作者的信息成功", bookDataStatus);
    }


    //    禁书
    @GetMapping("findupdatbook")
    public Result findupdatbook(Integer bookid) {
//        根据条件查询
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Book> bookid1 = queryWrapper.eq("book_id", bookid);
        Integer findupdatbook = editorService.findupdatbook(bookid);
        return new Result(true, StatusCode.OK, "修改状态成功，禁书成功", findupdatbook);
    }

//    作品审核
      @PostMapping("insertAuthor")
      public Result insertAuthor(){
        return  new Result(true,StatusCode.OK,"作品审核成功");
    }




}

