package com.woniuxy.vo;

import lombok.Data;

@Data
public class ApplicationVo {
    private String applicationId;
    private int userId;
    private String bookName;
    //审核意见表 1为同意，0为不同意
    private int code;

}
