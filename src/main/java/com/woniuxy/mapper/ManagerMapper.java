package com.woniuxy.mapper;

import com.woniuxy.model.Application;
import com.woniuxy.model.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.vo.*;
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
    //查询作者的前几个字段
    @Select("SELECT u.pen_name,u.`status`,u.isAuthor, author_id ,COUNT(book_id) AS totalBookNum,MAX(update_time) AS lastUpdateTime ,SUM(count) AS sumCount  FROM t_book b JOIN t_user u ON b.author_id= u.user_id GROUP BY author_id")
    List<MAuthorVO> findAllAuthor();
    //查询User的前5个字段
    @Select("SELECT user_name ,user_id ,status,last_login,account FROM t_user ")
    List<AllUserVo> findAllUser3();
    // 查询用户订阅数量
    @Select("SELECT user_id,COUNT(collection_id) AS totalSub FROM t_collection")
    List<MSubsVo> selectTotalSub();
    //
    @Select("SELECT e.editor_id,e.tel,e.`status`,u.pen_name AS authorName ,b.book_name  FROM t_editor e JOIN t_user u ON e.editor_id=u.editor_id JOIN t_book b ON b.author_id=u.user_id")
    List<MEidtorVo> findAllEidtor();
    //查询t_book中的前几个字段
    @Select("SELECT b.book_id,b.book_name,image,author_id,status,count,b.count_day,update_time,flag,amont,bd.click_number,bd.collection,bd.subscribe FROM t_book b JOIN t_book_data bd ON b.book_id=bd.book_id")
    List<MBookVo> findAllBook();

    //查询用户的收藏数
    @Select("SELECT user_id,COUNT(collection_id)FROM t_collection ")
    List<MCollectionVo> findUserCollect();
    //获取申请信息
    List<Application> getApplication();
    //查看合同
    @Select("SELECT u.pen_name AS authorName,b.author_id,u.status,u.user_tel,u.author_time,u.degree,u.bankCard ,b.book_id,b.book_name,b.`status` AS bookStatus FROM t_book b JOIN t_user u ON b.author_id=u.user_id WHERE u.isAuthor=3")
    List<ContractVo> getContract();
}
