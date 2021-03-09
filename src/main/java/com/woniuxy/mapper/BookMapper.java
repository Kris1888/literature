package com.woniuxy.mapper;

import com.woniuxy.model.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface BookMapper extends BaseMapper<Book> {
    //根据作者笔名模糊搜索其所有作品
    @Select("SELECT b.* " +
            "FROM t_book AS b " +
            "JOIN t_user AS u " +
            "ON u.user_id=b.author_id " +
            "WHERE u.pen_name LIKE #{pen_name}")
     List<Book> selectPenNameAll(String pen_name);
}
