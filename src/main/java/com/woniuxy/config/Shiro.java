//package com.woniuxy.config;
//
//
//import com.woniuxy.filter.JwtFilter;
//import com.woniuxy.realm.CustomerRealm;
//import com.woniuxy.realm.Myrealm;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class Shiro {
////注册realm
//    @Bean
//
//    public Realm realm(){
//        CustomerRealm customerRealm=new CustomerRealm();
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("md5");
//        credentialsMatcher.setHashIterations(1024);//设置散列次数
//        customerRealm.setCredentialsMatcher(credentialsMatcher);
//        return customerRealm;
//    }
////    注册DefaultWebSecurityManager
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(){
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        defaultWebSecurityManager.setRealm(realm());
////        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
//                return defaultWebSecurityManager;
//                }
//    //注册ShiroFilterFactoryBean
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
//        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//        return  shiroFilterFactoryBean;
//
//
////        filters.put("jwt",new JwtFilter());
////
////        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
////        stringStringLinkedHashMap.put("/user/login","anon");
////        stringStringLinkedHashMap.put("/**","jwt");
////
////
////        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringLinkedHashMap);
//////        shiroFilterFactoryBean.setLoginUrl("/toLogin");
//////        shiroFilterFactoryBean.setLoginUrl("/login.html");
////        return shiroFilterFactoryBean;
//
//
//    }
//
//}
//
