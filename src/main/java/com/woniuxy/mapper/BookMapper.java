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
            "WHERE u.pen_name LIKE #{penName}")
     List<Book> selectPenNameAll(String penName);
    //根据书籍id查询书籍所有信息
    @Select("SELECT * " +
            "FROM t_book " +
            "WHERE book_id=#{bookId}")
    List<Book> selectBookIdfindAll(Integer bookId);
    //根据书名模糊搜索
    @Select("SELECT t_book.* " +
            "FROM t_book " +
            "WHERE book_name LIKE #{bookName}")
    List<Book> selectBookNameFindAll(String bookName);
    //查询订阅数在前十的热门书籍
    @Select("SELECT * " +
            "FROM  " +
            "t_book AS b " +
            "JOIN t_book_data AS d " +
            "ON b.book_id=d.book_data_id " +
            "GROUP BY d.subscribe ORDER BY d.subscribe DESC  LIMIT 10 ;")
    List<Book> selectBookDataANDdesc();
}
