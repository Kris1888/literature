//package com.woniuxy.realm;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//
////shiro配置类
//@Configuration
//public class ShiroConfig {
//////    1.
////    @Bean
////    public Realm realm(){
////        MyRealm myRealm=new MyRealm();
////        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
////        matcher.setHashAlgorithmName("md5");
////        matcher.setHashIterations(2048);
////        myRealm.setCredentialsMatcher(matcher);
////        return myRealm;
////    }
////    2.
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(){
//        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
//        securityManager.setRealm(realm());
////        将remeber加入配置
//        securityManager.setRememberMeManager(cookieRememberMeManager());
//        return securityManager;
//    }
////    3.
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
//
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        //        设置黑白名单
//        filterChainDefinitionMap.put("/js/**","anon");
//        filterChainDefinitionMap.put("/css/**","anon");
//        filterChainDefinitionMap.put("/user/login","anon");
//        filterChainDefinitionMap.put("/user/register","anon");
//        filterChainDefinitionMap.put("/register.html","anon");
//        filterChainDefinitionMap.put("/user/logout","logout");//退出登录
////        设置黑名单
//        filterChainDefinitionMap.put("/**","user");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        return shiroFilterFactoryBean;
//    }
//
//
//    @Bean
//    public CookieRememberMeManager cookieRememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        SimpleCookie rememberMe = new SimpleCookie("rememberMe");
//        rememberMe.setMaxAge(7*24*60*60);
//        cookieRememberMeManager.setCookie(rememberMe);
//        cookieRememberMeManager.setCipherKey(Base64.decode("a1b2c3d4e5f6g7h8i9j10k=="));
//        return cookieRememberMeManager;
//    }
//
//}
