package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BookCommitVO {
    private String bookName;
    private String commitContent;
    private String commitDate;
//    评论是否为加精
    private Integer status;
}
