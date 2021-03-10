package com.woniuxy.mapper;

import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
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
public interface CategoryMapper extends BaseMapper<Category> {
    //根据分类id查询分类下的所有书籍
    @Select("SELECT b.*" +
            "FROM t_category AS c" +
            "JOIN t_book AS b" +
            "ON c.category_id=b.category_id" +
            "WHERE c.category_id=#{c.category_id}")
    List<Book> selectCategoryByIdAndBook(String category_id);

}
