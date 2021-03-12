package com.woniuxy.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.JWTUtil;
import com.woniuxy.util.SaltUtils;
import com.woniuxy.vo.*;
import org.apache.shiro.crypto.hash.Md5Hash;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    private UserMapper userMapper;
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
@PostMapping("login")
public Result login(@RequestBody UserVO2 userVO, HttpSession session){
//        //在shir配置类中配置了securitymanager,会自动注入到secuerityutils中
//        Subject subject = SecurityUtils.getSubject();
//        //创建令牌 存入账户信息
//        //加入remberme
//        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUsername(),userVO.getPassword(),userVO.isChecked());
//        //登录
//        try {
//            subject.login(token);
//            return new Result(true,StatusCode.OK,"账户密码匹配，正在跳转。。。");
//        }catch (UnknownAccountException e){
//            e.printStackTrace();
//            System.out.println("用户名错误");
//            return new Result(false,StatusCode.ERROR,"账户密码不匹配");
//        }catch (IncorrectCredentialsException e) {
//            e.printStackTrace();
//            System.out.println("密码错误");
//            return new Result(false, StatusCode.ERROR, "账户密码不匹配");
//        }

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("user_name", userVO.getUsername());
    User userDB = userService.getOne(queryWrapper);
    if (!org.springframework.util.ObjectUtils.isEmpty(userDB)) {
        Md5Hash md5Hash = new Md5Hash(userVO.getPassword(), userDB.getSalt(), 2048);
        String md5Password = md5Hash.toHex();
        if (md5Password.equals(userDB.getPassword())) {
            //创建jwt并返回
            HashMap<String, String> map = new HashMap<>();
            map.put("user_name", userVO.getUsername());
            String jwtToken = JWTUtil.createToken(map);
            return new Result(true, StatusCode.OK, "登录成功", jwtToken);

        } else {
            return new Result(false, StatusCode.ERROR, "密码错误");
        }
    } else {
        return new Result(false, StatusCode.ERROR, "尚未注册");

    }


}

    //修改个人信息
    @PostMapping("updatePersonalInformation")
    public Result UpdatePersonalInformation (String user, MultipartFile file) throws IOException {
        //        设置封面图片名称前缀
        String uploadFileNamePrefix=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+ UUID.randomUUID().toString().replace("-","");
//        设置封面图片名称后缀
        String originalFilename = file.getOriginalFilename();
//        设置封面图片全名
        String filename=uploadFileNamePrefix+originalFilename;
//        书籍的封面上传到服务器保存
        file.transferTo(new File("D:/tool/myvuecli/src/assets/"+filename));
        User user1 = JSONObject.parseObject(user,User.class);
        user1.setUserimg(filename);
        String a=user1.getBirthday();
        String newBirthday= a.substring(0,10);
        user1.setBirthday(newBirthday);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","晚上无风");//先用一个写死的用户名测试,后续接上用户登录的用户名
        userService.update(user1,wrapper);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user1.getUserId());
        User userDB = userMapper.selectOne(queryWrapper);
        String userimg= userDB.getUserimg();
        return new Result(true,StatusCode.OK,"修改个人资料成功",userimg);
    }
//修改密码
    @PostMapping("updatePassword")
    public Result UpdatePassword (@RequestBody UserVO userVO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name","晚上无风");//先用一个写死的用户名测试,后续接上用户登录的用户名
        User userDB = userService.getOne(queryWrapper);
        String salt = userDB.getSalt();
        Md5Hash md5Hash = new Md5Hash(userVO.getPassword(), salt, 2048);

        if(userDB.getPassword().equals(md5Hash.toHex())){

            userDB=new User();
            salt=SaltUtils.getSalt(8);
            md5Hash= new Md5Hash(userVO.getNewpassword(), salt, 2048);
            userDB.setPassword(md5Hash.toHex());
            userDB.setSalt(salt);
            userService.update(userDB,queryWrapper);
            return new Result(true,StatusCode.OK,"修改密码成功");

        }
        return new Result(false,StatusCode.ERROR,"修改失败,请重试");
    }
    //查询收藏书籍
    @PostMapping("findMyCollectByUid")
    public Result FindMyCollectByUid (@RequestBody PageVO pageVO){
        QueryWrapper<BookVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c.user_id",pageVO.getUid());
        Page<BookVO> Page = new Page<>(pageVO.getCurrent(),pageVO.getSize());
        Page<BookVO>  bookVOPage  = userMapper.findAllBooks(Page,queryWrapper);
//        System.out.println(bookVOPage);
//        List<BookVO> records = bookVOPage.getRecords();

        return new Result(true,StatusCode.OK,"查询成功",bookVOPage);



    }
//搜索某本收藏书籍
    @GetMapping("findBookNameandAuthor/{uid}/{input}")
    public Result findBookNameandAuthor(@PathVariable Integer uid,@PathVariable String input){

        List<BookVO> bookNameandAuthor = userMapper.findBookNameandAuthor(uid,input);

        return new Result(true,StatusCode.OK,"搜索成功",bookNameandAuthor);
    }
    //取消收藏
    @GetMapping("handleEdit/{bookName}")
    public Result HandleEdit(@PathVariable String bookName){
        userMapper.deleteByBookName(bookName);
        return new Result(true,StatusCode.OK,"删除成功");
    }
//查询订阅书籍
    @PostMapping("findMysubscribeByUid")
    public Result findMysubscribeByUid (@RequestBody PageVO pageVO){
        QueryWrapper<BookVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s.user_id",pageVO.getUid());
        Page<BookVO> Page = new Page<>(pageVO.getCurrent(),pageVO.getSize());
        Page<BookVO>  bookVOPage  = userMapper.findAllScribeBooks(Page,queryWrapper);
        return new Result(true,StatusCode.OK,"查询成功",bookVOPage);
    }

    //搜索某本订阅书籍
    @GetMapping("findSubscribeBookName/{uid}/{input}")
    public Result findSubscribeBookName(@PathVariable Integer uid,@PathVariable String input){

        List<BookVO> bookNameandAuthor = userMapper.findSubscribeBookName(uid,input);

        return new Result(true,StatusCode.OK,"搜索成功",bookNameandAuthor);
    }

    //成为作者,先验证这个
    @GetMapping("findIsAuthor/{uid}")
    public Result findIsAuthor(@PathVariable Integer uid){
        Integer isAuthor = userMapper.findIsAuthor(uid);
        System.out.println(isAuthor);
         if (isAuthor==1){
             return new Result(false,StatusCode.ERROR,"您已经是作者,不可再次申请为作者");
         }
        return new Result(true,StatusCode.OK,"验证成功,可成为作者");
    }
    //申请成为作者,把填写的数据录入数据库中
    @PostMapping("insertAuthor")
    public Result insertAuthor(@RequestBody User user){
    UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("t_user.user_id",user.getUserId());
        userUpdateWrapper.set("pen_name",user.getPenName());
        userUpdateWrapper.set("name",user.getName());
        userUpdateWrapper.set("address",user.getAddress());
        userUpdateWrapper.set("isCard",user.getIsCard());
        userUpdateWrapper.set("bankCard",user.getBankCard());
        userUpdateWrapper.set("isAuthor",user.getIsAuthor());
        userMapper.update(user,userUpdateWrapper);
        return new Result(true,StatusCode.OK,"申请成功");

    }
    //查询余额
    @GetMapping("findMyAccount/{uid}")
    public Result findMyAccount(@PathVariable Integer uid){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",uid);
        User userDB = userMapper.selectOne(queryWrapper);
        Integer account=userDB.getAccount();

        return new Result(true,StatusCode.OK,"查询余额成功",account);

    }
//    //充值
//    @GetMapping("RechargeMoney/{money}/{uid}")
//    public Result RechargeMoney(@PathVariable Integer money,@PathVariable Integer uid){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",uid);
//        User userDB = userMapper.selectOne(queryWrapper);
//        Integer account=userDB.getAccount();
////        if (money==null){
////
////            account=account+1000;
////            userDB.setAccount(account);
////        }else {
//            account=account+money;
//        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
//        userUpdateWrapper.eq("t_user.user_id",uid);
//        userUpdateWrapper.set("account",account);
//        userMapper.update(userDB,userUpdateWrapper);
////        }
//        return new Result(true,StatusCode.OK,"充值成功");

//    }
}

