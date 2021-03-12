package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;
@Data
public class AllUserVo {
    private String userName;
    private Date lastLogin;
    private int account;
    private int userId;
    private String status;
    private int totalSub;

}
