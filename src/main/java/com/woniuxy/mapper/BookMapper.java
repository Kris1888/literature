package com.woniuxy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.model.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.Commit;
import com.woniuxy.vo.BookCommitVO;
import com.woniuxy.vo.BookVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface BookMapper extends BaseMapper<Book> {
//    根据作者的ID查询该作者的作品 点赞数 连载状态 收藏数 订阅数
    @Select("select tb.book_name,tb.status,tbd.click_number,tbd.collection,tbd.subscribe from t_user tu join t_book tb on tu.user_id=tb.author_id join t_book_data tbd on tb.book_id=tbd.book_id where tu.user_id=#{userId}")
     BookVO[] getBookDataByUserId(Integer userId);
//    根据作者的ID分页查询该作者所有书籍的评论
    @Select("select tb.book_name,tc.commit_content,tc.commit_date,tc.status from t_commit tc join t_book tb on tc.book_id=tb.book_id join t_user tu on tu.user_id=tb.author_id ${ew.customSqlSegment}")
    Page<BookCommitVO> getBookCommitByUserId(Page<BookCommitVO> page, @Param(Constants.WRAPPER) QueryWrapper<BookCommitVO> queryWrapper);
//    根据评论修改其是否为精华评论
    @Update("update t_commit set status=#{status} where commit_content=#{commitContent}")
    void changeBookCommitStatusByCommitContent(String commitContent,Integer status);
}
