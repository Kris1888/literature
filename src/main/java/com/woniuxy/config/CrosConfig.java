package com.woniuxy.config;

import com.woniuxy.dto.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")    //所有路径
                .allowedOrigins("*")             //授权的源,springboot版本较高时，与allowCredentials(true)冲突
//                .allowedOriginPatterns("*")        //授权的源，冲突时使用该配置,2.3.5spring坂本使用这个
              .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")//请求方式
//                .allowedMethods("*")
                .allowCredentials(true)            //是否开启ajax相关的cookie提交
                .allowedHeaders("*")               //控制哪些header能发送请求
                .maxAge(3600);//跨域请求有效期

    }

}