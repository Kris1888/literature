package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.CollectionMapper;
import com.woniuxy.mapper.SubscribeMapper;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.User;
import com.woniuxy.realm.Myrealm;
import com.woniuxy.service.UserService;
import com.woniuxy.util.SaltUtils;
import com.woniuxy.util.SendMessage;
import com.woniuxy.vo.SetRoleVO;
import com.woniuxy.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.util.Random;

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
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private SubscribeMapper subscribeMapper;

    String code = null;
    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO,HttpServletRequest request) {
        System.out.println(userVO);
        //获取短信验证码
//        String code = (String)request.getAttribute("code");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userVO.getUserName());
        User userDB = userService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(userDB)) {
            //比较验证码是否输入正确
            if (userVO.getTelcode().equals(code)){
                String salt = SaltUtils.getSalt(8);
                User user = new User();
                Md5Hash md5Hash = new Md5Hash(userVO.getPassword(), salt, 2048);
                // 保存加密的密码
                user.setUserName(userVO.getUserName());
                user.setUserTel(userVO.getUserTel());
                user.setPassword(md5Hash.toHex());
                user.setSalt(salt);
                //        存到数据中
                userService.save(user);
                return new Result(true, StatusCode.OK, "注册成功");
            }else {
                return new Result(false,StatusCode.YANZHENMA,"验证码输入错误");
            }

        } else {
            return new Result(true, StatusCode.ZHUANGHAOYIBEIZHUCE, "已经被注册");
        }
    }

    @PostMapping("SendMessage")
    public Result SendMessage(@RequestBody UserVO userVO,HttpSession session){
        System.out.println(userVO);
        //根据电话查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_tel", userVO.getUserTel());
        User user = userMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(user)) {
            //给用户手机发送短信验证码
//      code = SendMessage.sendMessage(userVO.getUser_tel());
            code = "111111";
            //将短信验证码存入request域中
//        session.setAttribute("code",code);
            return new Result(true,StatusCode.OK,"短信发送成功",code);
        }else if(!ObjectUtils.isEmpty(userVO.getUserName())&&!ObjectUtils.isEmpty(userVO.getUserTel())){
            //给用户手机发送短信验证码
//      code = SendMessage.sendMessage(userVO.getUser_tel());
            code = "111111";
            //将短信验证码存入request域中
//        session.setAttribute("code",code);
            return new Result(true,StatusCode.OK,"短信发送成功",code);
        } else {
            return new Result(false,StatusCode.LOGINERROR,"手机号码输入错误或未注册");
        }

//        return null;
    }

    @PostMapping("login")
    public Result login(@RequestBody UserVO userVo, HttpSession session) {
        System.out.println(userVo.getUserName() + "user_name");//账号
        System.out.println(userVo.getPassword() + "password");//密码
        System.out.println(userVo.getUserTel() + "user_tel");//电话
        System.out.println(userVo.getChecked() + "check");

        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUserName(),userVo.getPassword(),userVo.getChecked());
        //用户名登录
        if (!ObjectUtils.isEmpty(userVo.getUserName())){
            if (!ObjectUtils.isEmpty(userVo)){
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_name", userVo.getUserName());
                User user = userMapper.selectOne(queryWrapper);
//                System.out.println(user.getSalt());

                if (!ObjectUtils.isEmpty(user)){
                    Md5Hash md5Hash = new Md5Hash(userVo.getPassword(),user.getSalt(),2048);
                    String loginPassword = md5Hash.toHex();
                    System.out.println(loginPassword);
                    if (loginPassword.equals(user.getPassword())){
                        return new Result(true, StatusCode.OK,"登录成功",user);
                    } else {
                        return new Result(false,StatusCode.LOGINERROR,"密码错误");
                    }
                }else {
                    return new Result(false,StatusCode.LOGINERROR,"用户不存在");
                }

            }
        }else if (!ObjectUtils.isEmpty(userVo.getUserTel())){
            //根据电话查询用户信息
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_tel", userVo.getUserTel());
            User user = userMapper.selectOne(queryWrapper);
            System.out.println(userVo.getTelcode());
            //获取短信验证码
//            String code = (String)session.getAttribute("code");
//            System.out.println(code);
            if (!ObjectUtils.isEmpty(user)){
                System.out.println(userVo.getTelcode()+":"+code);
                if (userVo.getTelcode().equals(code)){
                    return new Result(true, StatusCode.OK,"登录成功",user);
                }else {
                    return new Result(false,StatusCode.YANZHENMA,"验证码输入错误");
                }
            }else {
                return new Result(false,StatusCode.LOGINERROR,"手机号码输入错误或未注册");
            }

        }
            return new Result(false, StatusCode.SHURUBUENNGWEIKONG, "请输入手机号码", userVo);
    }
   @PostMapping("/addsubscription")
    public Result addSubscription(@RequestBody String message){
       System.out.println(message);
       String cc = message.substring(21);
       String coo = message.substring(34);
       String uId = cc.substring(0,cc.indexOf("\""));
       String bkId = coo.substring(0,coo.indexOf("}"));
       Integer userId = Integer.valueOf(uId);
       Integer bookId = Integer.valueOf(bkId);
       Random random = new Random();
       subscribeMapper.INSETTsuBCRIBE(userId,random.nextInt(200),bookId);
       return new Result(true,StatusCode.OK,"订阅成功");
   }
    @PostMapping("/addcollect")
    public Result addCollect(@RequestBody String message){
        System.out.println(message);
        String cc = message.substring(21);
        String coo = message.substring(34);
        String uId = cc.substring(0,cc.indexOf("\""));
        String bkId = coo.substring(0,coo.indexOf("}"));
        Integer userId = Integer.valueOf(uId);
        Integer bookId = Integer.valueOf(bkId);
        collectionMapper.INSERTCOLLECTION(userId,bookId);
        return new Result(true,StatusCode.OK,"收藏成功");
    }
}


