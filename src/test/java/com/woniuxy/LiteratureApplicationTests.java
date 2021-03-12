package com.woniuxy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.util.UUIDUtil;
import com.woniuxy.vo.BookVO;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LiteratureApplicationTests {
    @Resource
private UserMapper userMapper;
    @Test
    void contextLoads() {


    }


        @Test
        public void test3(){
            System.out.println(UUIDUtil.getUUID().replace("-", ""));
            System.out.println(UUIDUtil.getUUID().replace("-", ""));
            System.out.println(UUIDUtil.getUUID().replace("-", ""));

    }

}
