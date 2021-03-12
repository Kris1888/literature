package com.woniuxy.vo;

import lombok.Data;

@Data
public class UserVO {
    private String username;
    private String password;
    private String newpassword;
    private String confirm;
}
