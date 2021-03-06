//package com.woniuxy.component;
//
//import com.woniuxy.mapper.RoleMapper;
//import com.woniuxy.mapper.UserMapper;
//import com.woniuxy.model.Permission;
//import com.woniuxy.model.User;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//public class CustomerRenlm extends AuthorizingRealm {
//    @Resource
//    private UserMapper userMapper;
//    @Resource
//    private RoleMapper roleMapper;
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //进行授权安排
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        User user = (User) principalCollection.getPrimaryPrincipal();
//        //查询当前用户拥有哪些角色
//        List<String> roles = roleMapper.findRolesByUid(user.getId());
//       if (!CollectionUtils.isEmpty(roles)){
//           //如果查询到的角色集合不为空开始授予角色
//
//           roles.forEach(role -> simpleAuthorizationInfo.addRole(role));
//       }
//        //查询当前用户拥有哪些权限
//        List<Permission> permissions = userMapper.findMenusByUid(user.getId());
//        if (!CollectionUtils.isEmpty(permissions)){
//            //如果查询到的权限集合不为空开始授予角色
//
//            permissions.forEach(permission -> simpleAuthorizationInfo.addStringPermission(permission.getElement()));
//        }
//        return simpleAuthorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        return null;
//    }
//}
