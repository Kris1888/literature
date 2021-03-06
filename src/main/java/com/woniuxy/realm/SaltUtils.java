package com.woniuxy.realm;

import java.util.Random;

public class SaltUtils {
    public static String getSalt(Integer count) {
        //使用参数决定生成的随机盐长度
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVVWXYZ1234567890<>?!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            char achar = chars[new Random().nextInt(chars.length)];
            sb.append(achar);

        }
        return sb.toString();
    }
}