package com.woniuxy.dto;

public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static  final int YANZHENMA=20004;//验证码错误
    public static  final  int SHURUBUENNGWEIKONG=20005;//输入不能为空
    public static final int ZHUANGHAOYIBEIZHUCE=20006;//账号已被注册
    public static final int CHAXUNWEIKONG=20007;//查询结果为空

}