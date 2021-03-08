package com.woniuxy.mapper;

import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.User;
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
public interface EditorMapper extends BaseMapper<Editor> {

    //    查询所有书籍
    @Select("select * from t_book where flag=1")
    public List<Book> findallBook();


    //    查询作者
    @Select("SELECT * from  t_user WHERE isAuthor=1")
    public List<User> findallEditor();

//    查询签约作品



//    查看作品的数据'
    @Select("SELECT * from  t_book_data")
    public String findallBookdata();


//    查询指定作者的信息



//    禁书
    @Select("SELECT * from t_user WHERE `status`=1")
    public String findupdatbook();


//    作品审核

}
