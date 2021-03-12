package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

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

    String code = null;
    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO,HttpServletRequest request) {
        System.out.println(userVO);
        //获取短信验证码
        String code = (String)request.getAttribute("code");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userVO.getUser_name());
        User userDB = userService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(userDB)) {
            //比较验证码是否输入正确
            if (userVO.getTelcode().equals(code)){
                String salt = SaltUtils.getSalt(8);
                User user = new User();
                Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 2048);
                // 保存加密的密码
                user.setUser_name(user.getUser_name());
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
        //给用户手机发送短信验证码
//      code = SendMessage.sendMessage(userVO.getUser_tel());
        code = "111111";
        //将短信验证码存入request域中
//        session.setAttribute("code",code);

        return new Result(true,StatusCode.OK,"短信发送成功",code);
//        return null;
    }

    @PostMapping("login")
    public Result login(@RequestBody UserVO userVo, HttpSession session) {
        System.out.println(userVo.getUser_name() + "user_name");//账号
        System.out.println(userVo.getPassword() + "password");//密码
        System.out.println(userVo.getUser_tel() + "user_tel");//电话
        System.out.println(userVo.getChecked() + "check");

        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUser_name(),userVo.getPassword(),userVo.getChecked());
        if (!ObjectUtils.isEmpty(userVo.getUser_name())){
            if (!ObjectUtils.isEmpty(userVo)){
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_name", userVo.getUser_name());
                User user = userMapper.selectOne(queryWrapper);
                System.out.println(user.getSalt());
                Md5Hash md5Hash = new Md5Hash(userVo.getPassword(),user.getSalt(),2048);
                String loginPassword = md5Hash.toHex();
                System.out.println(loginPassword);
                if (loginPassword.equals(user.getPassword())){
                    return new Result(true, StatusCode.OK,"登录成功",user);
                }else {
                    return new Result(false,StatusCode.LOGINERROR,"密码错误");
                }
            }
        }else if (!ObjectUtils.isEmpty(userVo.getUser_tel())){
            System.out.println(userVo.getTelcode());
            //获取短信验证码
//            String code = (String)session.getAttribute("code");
//            System.out.println(code);
            //根据电话查询用户信息
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_tel", userVo.getUser_tel());
            User user = userMapper.selectOne(queryWrapper);
            if (!ObjectUtils.isEmpty(user)){
                System.out.println(userVo.getTelcode()+":"+code);
                if (userVo.getTelcode().equals(code)){
                    return new Result(true, StatusCode.OK,"登录成功",user);
                }else {
                    return new Result(false,StatusCode.YANZHENMA,"验证码输入错误");
                }
            }else {
                return new Result(false,StatusCode.LOGINERROR,"手机号码输入错误");
            }

        }
            return new Result(false, StatusCode.SHURUBUENNGWEIKONG, "请输入手机号码", userVo);
    }
}


