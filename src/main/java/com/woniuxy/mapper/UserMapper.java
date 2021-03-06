package com.woniuxy.mapper;


import com.woniuxy.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.woniuxy.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-06
 */
public interface UserMapper extends BaseMapper<User> {

}
