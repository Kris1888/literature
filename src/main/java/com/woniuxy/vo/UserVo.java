package com.woniuxy.vo;

import lombok.Data;

@Data
public class UserVO {
    private String user_name;
    private String password;
    private String user_tel;
    private Boolean checked;
    private String telcode;
}
