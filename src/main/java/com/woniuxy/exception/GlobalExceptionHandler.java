package com.woniuxy.exception;

import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常类
@RestControllerAdvice//返回json格式
public class GlobalExceptionHandler {
    @ExceptionHandler(UnknownAccountException.class)
    public Result handleUnknownAccountException(){
        return new Result(false, StatusCode.ERROR,"未注册",null);
    }
    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result handlerIncorrectCredentialsException(){
        return new Result(false, StatusCode.ERROR,"密码错误",null);
    }
}