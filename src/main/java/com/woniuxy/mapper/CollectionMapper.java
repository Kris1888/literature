package com.woniuxy.mapper;

import com.woniuxy.model.Collection;
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
public interface CollectionMapper extends BaseMapper<Collection> {
    //根据用户id和书籍id新增收藏
    @Insert("INSERT  INTO t_collection(user_id,book_id) VALUES(#{userId},#{bookId})")
    public  void INSERTCOLLECTION(Integer userId,Integer bookId);

}
