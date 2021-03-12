package com.woniuxy.vo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class PayInfoVO {
    private String outTradeNo;
    private String subject;
    private Integer totalAmount;
    private String body;
}
