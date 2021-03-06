//package com.woniuxy.vo;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.woniuxy.mapper.UserMapper;
//import com.woniuxy.model.User;
//import com.woniuxy.molde.User;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.util.ObjectUtils;
//
//import javax.annotation.Resource;
//
//
//public class MyRealm extends AuthorizingRealm {
//@Resource
//private UserMapper userMapper;
//
////授权
//@Override
//protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    return null;
//}
////认证
//@Override
//protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
////   进行登录认证
//    String username = (String) authenticationToken.getPrincipal();
////数据可以查
//    QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//    queryWrapper.eq("username",username);
//    User user = userMapper.selectOne(queryWrapper);
////先判断
//    if(!ObjectUtils.isEmpty(user)){
//        return new SimpleAuthenticationInfo(user,
//                user.getPassword(),
//                ByteSource.Util.bytes(user.getSalt())
//               ,this.getName());
//          }
//    return null;
//}
//}
