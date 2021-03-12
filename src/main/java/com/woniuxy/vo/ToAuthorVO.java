package com.woniuxy.vo;

import com.woniuxy.model.User;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class ToAuthorVO  {
    private String penName;
    private String name;
    private String address;
    private String idCard;
    private String bankCard;

}
