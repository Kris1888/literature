package com.woniuxy.vo;

import lombok.Data;

@Data
public class UserVO {
    private String userName;
    private String password;
    private String userTel;
    private Boolean checked;
    private String telcode;
}
