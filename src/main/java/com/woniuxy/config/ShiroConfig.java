package com.woniuxy.config;


import com.woniuxy.filter.JwtFilter;
import com.woniuxy.realm.Myrealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
//注册realm
    @Bean

    public Realm realm(){
          Myrealm myrealm = new Myrealm();
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("md5");
//        credentialsMatcher.setHashIterations(1024);//设置散列次数
//        myrealm.setCredentialsMatcher(credentialsMatcher);
//        Myrealm myrealm = new Myrealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2048);//设置散列次数
        myrealm.setCredentialsMatcher(credentialsMatcher);
        return myrealm;
    }
//    注册DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm());
//        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager());
                return defaultWebSecurityManager;
                }
    //注册ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("jwt",new JwtFilter());

        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        stringStringLinkedHashMap.put("/user/login","anon");
        stringStringLinkedHashMap.put("/manager/login","anon");
        stringStringLinkedHashMap.put("/**","jwt");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringLinkedHashMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }
    //记住我
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie rememberMe = new SimpleCookie("rememberMe");
        rememberMe.setMaxAge(7*24*60*60);
        cookieRememberMeManager.setCookie(rememberMe);
        cookieRememberMeManager.setCipherKey(Base64.decode("a1b2c3d4e5f6g7h8i9j10k=="));
        return cookieRememberMeManager;
    }

}

