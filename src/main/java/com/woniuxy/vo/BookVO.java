package com.woniuxy.vo;

import com.woniuxy.model.Book;
import lombok.Data;

@Data
public class BookVO {
    private String bookName;
    private Integer clickNumber;
    private Integer collection;
    private Integer subscribe;
}
