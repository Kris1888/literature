package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MBookVo {
    //作品id
    private int bookId;
    //作品名字
    private String bookName;
    //作品的作者
    private int authorId;
    //最近更新时间
    private Date updateTime;
    //更新天数
    private int countDay;
    //作品状态
    private  int flag;
    //作品字数
    private  int count;

    //作品的状态
    private String status;
    //作品的订阅量
    private int subscribe;
    //作品的单价
    private int amont;
    //作品的收益
    private int profit;
    //作品的点击量
    private int clickNumber;
    //作品的收藏量
    private int collection;


}
