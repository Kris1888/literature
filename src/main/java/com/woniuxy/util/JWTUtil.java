package com.woniuxy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JWTUtil {
    //工具类的目的:将创建和解析jwt的代码抽取出来,实现代码的复用

    private static final Long EXPIRE_TIME=6*60*60*1000l;//设置过期时间
    private static final String SIGN=SaltUtils.getSalt(16); //随机签名
    public static String createToken(HashMap<String, String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        builder.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME));
        String token = builder.sign(Algorithm.HMAC256(SIGN));


        return token;
    }

    //解析gwt
    public static DecodedJWT verify(String token){

        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
}
