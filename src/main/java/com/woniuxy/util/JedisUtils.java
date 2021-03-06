package com.woniuxy.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
    private static JedisPool jp=null;
    private static String host=null;
    private static Integer port;
//    private static Integer maxTotal;
//    private static Integer maxIdle;

    static {
        //读取配置文件
        ResourceBundle rb=ResourceBundle.getBundle("jedis");
        host = rb.getString("jedis.host");
        port = Integer.parseInt(rb.getString("jedis.port"));
//        maxTotal = Integer.parseInt(rb.getString("jedis.maxTotal"));
//        maxIdle = Integer.parseInt(rb.getString("jedis.maxIdle"));

        JedisPoolConfig jpc = new JedisPoolConfig();
//        jpc.setMaxIdle(maxIdle);
//        jpc.setMaxTotal(maxTotal);
        //获取连接池对象
        jp = new JedisPool(jpc,host,port);
    }

    public static Jedis getJedis(){
        //从连接池中获取连接（jedis）
        return jp.getResource();
    }
}
