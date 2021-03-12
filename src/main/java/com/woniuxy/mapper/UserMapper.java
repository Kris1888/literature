package com.woniuxy.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.User;
import com.woniuxy.vo.BookVO;
import com.woniuxy.vo.ToAuthorVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
//查询收藏书籍
    @Select("SELECT u.pen_name,b.book_name,b.description FROM t_user u JOIN t_book b ON u.user_id=b.author_id JOIN t_collection c ON b.book_id=c.book_id ${ew.customSqlSegment}")
     Page<BookVO> findAllBooks(Page<BookVO> page,@Param(Constants.WRAPPER) QueryWrapper<BookVO> queryWrapper);
    //搜索某一本收藏的书籍
    @Select("SELECT u.pen_name,b.book_name,b.description FROM t_user u JOIN t_book b ON u.user_id=b.author_id JOIN t_collection c ON b.book_id=c.book_id  WHERE c.user_id=#{uid} and b.book_name=#{input} ")

    List<BookVO> findBookNameandAuthor(Integer uid,String input );
    //删除收藏的数据
    @Delete("DELETE FROM t_book WHERE book_name=#{bookName}")
    void deleteByBookName(String bookName);
   //查询订阅书籍
    @Select("SELECT u.pen_name,s.amont,b.book_name,b.description FROM t_user u JOIN t_book b ON u.user_id=b.author_id JOIN t_subscribe s ON b.book_id=s.book_id ${ew.customSqlSegment}")
    Page<BookVO> findAllScribeBooks(Page<BookVO> page,@Param(Constants.WRAPPER) QueryWrapper<BookVO> queryWrapper);
//搜索某本订阅的书籍
        @Select("SELECT u.pen_name,s.amont,b.book_name,b.description FROM t_user u JOIN t_book b ON u.user_id=b.author_id JOIN t_subscribe s ON b.book_id=s.book_id  WHERE s.user_id=#{uid} and b.book_name=#{input}")
        List<BookVO> findSubscribeBookName(Integer uid,String input );
//通过uid查询是否为作者
    @Select("SELECT t_user.isAuthor FROM t_user WHERE t_user.user_id=#{uid}")
    Integer findIsAuthor(Integer uid);
//    @Insert("")
//    void insertToBeAuthor(ToAuthorVO toAuthorVO);
}
