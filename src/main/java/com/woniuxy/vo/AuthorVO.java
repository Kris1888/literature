package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;
@Data
public class AuthorVO {
    private String author_name;
    private int author_id;
    //总作品数
    private int totalbook;
    //总字数
    private int totalcount;
    //总订阅金额
    private int totalamont;
    //最近更新的时间
    private Date lastupdate_time;




}
