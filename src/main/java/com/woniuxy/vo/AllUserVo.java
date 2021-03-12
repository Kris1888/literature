package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;
@Data
public class AllUserVo {

    private String user_name;
    private Date last_login;
    private int account;
    private int user_id;
    private int sumcollections;
    private int sumbook_id;

}
