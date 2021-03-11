package com.woniuxy.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import javax.annotation.Resource;
@Component
public class CustomerRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
//实现授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
//实现认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    String  username=(String) authenticationToken.getPrincipal();
     QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
         if(!ObjectUtils.isEmpty(user)){
         return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
         }
        return null;
    }
}