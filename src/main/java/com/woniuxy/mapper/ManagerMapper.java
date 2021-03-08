package com.woniuxy.mapper;

import com.woniuxy.model.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.User;
import com.woniuxy.vo.AllUserVo;
import com.woniuxy.vo.AuthorVO;
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
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("SELECT  u.user_name,b.author_id,COUNT(b.book_id) AS totalbook,SUM(b.count) AS totalcount,SUM(b.amont) AS totalamont,MAX(update_time) AS lastupdate_time FROM t_book b JOIN t_user u ON b.author_id =u.user_id")
    List<AuthorVO> findAllAuthor();
    @Select("SELECT  u.user_name,u.last_login,u.account,c.user_id ,COUNT(collection_id) AS sumcollection ,COUNT(s.book_id) AS sumsubscribe FROM t_collection c \n" +
            "JOIN " +
            "t_subscribe s " +
            "ON c.user_id=s.user_id JOIN t_user u ON c.user_id=u.user_id;")
    List<AllUserVo> findAllUser();
}
