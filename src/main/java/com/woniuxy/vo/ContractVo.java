package com.woniuxy.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ContractVo {
    private String authorName;
    private int authorId;
    private String status;
    private String userTel;
    private Date authorTime;
    private int degree;
    private String bankCard;
    private int bookId;
    private String bookName;

    private String bookStatus;

}
