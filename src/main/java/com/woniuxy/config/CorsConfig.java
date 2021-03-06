package com.woniuxy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置哪些路径可以跨域访问
        registry.addMapping("/**")
                //授权的源（那些源头发来的请求可以跨域）：根据springboot版本的不同
                //高版本的springboot
                //.allowedOriginPatterns("*")
                //2.2.5.RELEASE
                .allowedOrigins("*")
                //设置发送跨域的请求方式
                .allowedMethods("*")
                //设置哪些请求头可以发送跨域请求
                .allowedHeaders("*")
                //是否允许ajax附带cookie
                .allowCredentials(true)
                //设置重发跨域请求的最大时长
                .maxAge(3600);

    }
}
