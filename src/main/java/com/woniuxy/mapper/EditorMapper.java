package com.woniuxy.mapper;

import com.woniuxy.model.Book;
import com.woniuxy.model.BookData;
import com.woniuxy.model.Editor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.model.User;
import com.woniuxy.vo.EAuthorVo;
import com.woniuxy.vo.EditorVo;
import com.woniuxy.vo.EditorVofind;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface EditorMapper extends BaseMapper<Editor> {

    //登录
    @Select("SELECT * from t_editor WHERE tel=#{tel}")
    public Editor login(Editor editor);

    //    查询所有书籍
    @Select("select * from t_book")
    public List<Book> findallBook();


    //查询作者
    @Select("SELECT user_id,user_name,isAuthor from  t_user WHERE isAuthor=1")
    public List<User> findallEditor();

    //    查询签约作品
    @Select("SELECT book_id,book_name from t_book")
    public List<Book> findBook();


    //    查看作品的数据
    @Select("SELECT * from  t_book_data")
    public List<BookData> findallBookdata();


    //    查询指定作者的信息
    @Select("SELECT count(book_id) as sunbook,author_id as userId,sum(count) as Sumcount,max(update_time) as countDay from t_book")
    public List<EAuthorVo> findBookDataStatus();

    @Select("SELECT user_id as authorid,payment,user_name as username from t_user")
    public List<EditorVo> findUserPayment();


    //    禁书
    @Select("UPDATE t_book  set  flag=0 WHERE book_id=#{id}")
    public Integer findupdatbook();


//    作品审核



}
