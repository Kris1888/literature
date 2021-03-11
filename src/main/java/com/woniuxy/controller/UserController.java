package com.woniuxy.controller;


import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.*;
import com.woniuxy.model.*;
import com.woniuxy.service.UserService;
import com.woniuxy.util.SaltUtils;
import com.woniuxy.vo.BookCommitVO;
import com.woniuxy.vo.BookVO;
import com.woniuxy.vo.ChapterVO;
import com.woniuxy.vo.UserBookVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private BookMapper bookMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ChaptersMapper chaptersMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ApplicationMapper applicationMapper;
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

//作者可以新创建一个作品，提交审核，在审核结束之前，不得发布新作品
    @PostMapping("/addBookCheck")
    public Result addBookCheck(@RequestBody UserBookVO userBookVO){
        Application application = new Application();
        application.setBookName(userBookVO.getBookName());
        application.setStatus(0);
        application.setUserId(userBookVO.getUserId());
        application.setApplicationType("作品申请");
        applicationMapper.insert(application);
        return new Result(true, StatusCode.OK,"新增作品审核成功");
    }


//    根据书籍的名字查询书籍,查到result中data返回Book对象,未查到查到result中data返回空
    @PostMapping("/findTheBookByBookName")
    public Result findTheBookByBookName(@RequestBody Book book){
        if(book.getBookName()!=null){
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("book_name",book.getBookName());
            Book bookDB = bookMapper.selectOne(bookQueryWrapper);
            return new Result(true, StatusCode.OK,"查询作品成功",bookDB);
        }
            return new Result(false,StatusCode.ERROR,"该作品不存在");

    }

//    修改书籍的简介分类等信息
//    书籍的封面上传到服务器保存
    @RequestMapping("/editBookDetail")
    public Result editBookDetail(MultipartFile file, String book, HttpServletRequest request) throws IOException {
//        设置封面图片名称前缀
        String uploadFileNamePrefix=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+ UUID.randomUUID().toString().replace("-","");
//        设置封面图片名称后缀
        String originalFilename = file.getOriginalFilename();
//        设置封面图片全名
        String filename=uploadFileNamePrefix+originalFilename;
//        书籍的封面上传到服务器保存
        file.transferTo(new File("D:/head/"+filename));
        Book newBook = JSONObject.parseObject(book, Book.class);
        newBook.setImage(filename);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("book_name",newBook.getBookName());
        bookMapper.update(newBook,bookQueryWrapper);
        return new Result(true,StatusCode.OK,"修改作品简介成功");
    }

//根据作者的userId查询其作品的点赞 收藏 点击数据 作品状态
    @RequestMapping("/getBookData")
    public Result getBookData(@RequestBody User user){
        BookVO[] bookVOs = bookMapper.getBookDataByUserId(user.getUserId());
        System.out.println(bookVOs);
        return new Result(true, StatusCode.OK,"查询作品成功",bookVOs);
    }
//根据作者的userId查询其稿费
    @RequestMapping("/getAuthorPaymentByUserId")
    public Result getAuthorPaymentByUserId(@RequestBody User user){
        User userDB = userMapper.selectById(user.getUserId());
        Integer payment = userDB.getPayment();
        return new Result(true, StatusCode.OK,"查询作者稿费成功",payment);
    }

//    根据作品的名称更改作品的状态
    @RequestMapping("/changeBookStatus")
    public Result changeBookStatus(@RequestBody Book book){
        Result bookResultDB = findTheBookByBookName(book);
        Book bookDB = (Book)bookResultDB.getData();
        UpdateWrapper<Book> wrapper = new UpdateWrapper<>();
        wrapper.eq("book_name",bookDB.getBookName());
        bookDB.setStatus(book.getStatus());
        bookMapper.update(bookDB, wrapper);
        return new Result(true, StatusCode.OK,"修改作品状态成功");
    }
//    根据作者的ID查询该作者所有书籍的评论
    @RequestMapping("/getBookCommitByUserId/{index}")
    public Result getBookCommitByUserId(@PathVariable Integer index){
        QueryWrapper<BookCommitVO> wrapper = new QueryWrapper<>();
        wrapper.eq("tu.user_id",1);
        Page<BookCommitVO> page = new Page<>(index, 5);
        Page<BookCommitVO> bookCommitVOPage = bookMapper.getBookCommitByUserId(page, wrapper);
        List<BookCommitVO> records = bookCommitVOPage.getRecords();
        return new Result(true,StatusCode.OK,"查询作品评论成功",bookCommitVOPage);
    }

    //    根据评论的内容更改评论是否为精华评论
    @RequestMapping("/changeBookCommitStatus")
    public Result changeBookCommitStatus(@RequestBody Commit commit){
        bookMapper.changeBookCommitStatusByCommitContent(commit.getCommitContent(),commit.getStatus());
        return new Result(true, StatusCode.OK,"修改成功");
    }

//    根据书名新增章节
    @RequestMapping("/addChapterByBookName")
    public Result addChapterByBookName(@RequestBody ChapterVO chapterVO){
        Book bookTemp = new Book();
        bookTemp.setBookName(chapterVO.getBookName());
        Result result = findTheBookByBookName(bookTemp);
        Book bookDB =(Book) result.getData();
        Chapters chapters = new Chapters();
        chapters.setBookId(bookDB.getBookId());
        chapters.setChapterName(chapterVO.getChapterName());
        chapters.setContent(chapterVO.getContent());
        chapters.setWordNumber(chapterVO.getContent().length());
        chapters.setStatus(1);
        chapters.setUpdateTime(new Date());
        chaptersMapper.insert(chapters);
        return new Result(true, StatusCode.OK,"新增章节成功");
    }

//   根据作者的UserId查询其所有作品和作品字数,并筛选出大于三万字的作品
    @RequestMapping("/authorInfoBookSign")
    public Result authorInfoBookSign(@RequestBody User user){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id",user.getUserId());
        queryWrapper.ge("count",30000);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return new Result(true, StatusCode.OK,"查询可签约作品成功",books);
    }

//    根据作者的userId查询系统消息
    @RequestMapping("/getSystemMessage")
    public Result getSystemMessage(@RequestBody User user){
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        List<Message> messages = messageMapper.selectList(queryWrapper);
        return new Result(true, StatusCode.OK,"查询系统信息成功",messages);
    }

//    发送签约申请
     @RequestMapping("/authorInfoBookSignApplication")
    public Result authorInfoBookSignApplication(@RequestBody UserBookVO userBookVO){
         Application application = new Application();
         application.setUserId(userBookVO.getUserId());
         application.setBookName(userBookVO.getBookName());
         application.setStatus(0);
         application.setApplicationType("签约申请");
         applicationMapper.insert(application);
         return new Result(true, StatusCode.OK,"申请签约成功");
    }
}

