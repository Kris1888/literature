package com.woniuxy.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class String2Date implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if (!StringUtils.hasLength(s)) {
        throw new RuntimeException("参数不正确");
        }
        try {
            String pattern1="[0-9]{4}-[0-9]{2}-[0-9]{2}";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String pattern2="[0-9]{4}/[0-9]{2}/[0-9]{2}";
             SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
            Date date=null;
            if (s.matches(pattern1)) {
                return sdf1.parse(s);
            }
            if (s.matches(pattern2)) {
                return sdf2.parse(s);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
