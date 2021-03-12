package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;
@Data
public class MAuthorVO {
    //作者笔名
    private String authorName;
    private int authorId;
    //作者状态
    private String status;
    //是否签约
    private int isAuthor;
    //作品总数
    private String totalBookNum;

    //作者总的贡献字数
    private int sumCount;

    //作者所有书的所有收益
    private int sumProfit;
    //作者的所有的收藏量
    private int sumCollection;
    //作者总的订阅量
    private int sumSubs;
    //作者
    //最近更新的时间
    private Date lastUpdateTime;




}
