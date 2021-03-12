package com.woniuxy.mapper;

import com.woniuxy.model.Subscribe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface SubscribeMapper extends BaseMapper<Subscribe> {
    //根据用户id和书籍id订阅
    @Insert("INSERT INTO t_subscribe " +
            "(book_id,amont,user_id)VALUES(#{bookId},#{amont},#{userId})")
    public void INSETTsuBCRIBE(Integer userId,Integer amont, Integer bookId);

}
