package com.woniuxy.realm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mapper.PermissionMapper;
import com.woniuxy.mapper.RoleMapper;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.Role;
import com.woniuxy.service.PermissionService;
import com.woniuxy.service.RoleService;
import com.woniuxy.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


public class Myrealm extends AuthorizingRealm {

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserMapper usersMapper;
    @Resource
    private RoleMapper roleMapper;
    public boolean supports(AuthenticationToken token){
        return token instanceof  JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("授权开始");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String token = (String) principalCollection.getPrimaryPrincipal();
        DecodedJWT decodedJWT = JWTUtil.decodedToken(token);
        String username = decodedJWT.getClaim("username").asString();
//        List<Role> roles = roleMapper.getRolesByUserName(username);
//        roles.forEach(role->{
//            simpleAuthorizationInfo.addRole(role.getRolename());
//        });

        return simpleAuthorizationInfo;
        }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证开始");

        String token = (String) authenticationToken.getPrincipal();

        DecodedJWT verify = JWTUtil.decodedToken(token);
        String username = verify.getClaim("username").asString();
        if (username==token ){
            throw new AuthenticationException("token认证失败");
        }
        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
