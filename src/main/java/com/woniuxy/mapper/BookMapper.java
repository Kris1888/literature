package com.woniuxy.mapper;

import com.woniuxy.model.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.vo.BookVO;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface BookMapper extends BaseMapper<Book> {
    @Select("select tb.book_name,tb.status,tbd.click_number,tbd.collection,tbd.subscribe from t_user tu join t_book tb on tu.user_id=tb.author_id join t_book_data tbd on tb.book_id=tbd.book_id where tu.user_id=#{userId}")
    public BookVO[] getBookDataByUserId(Integer userId);
}
