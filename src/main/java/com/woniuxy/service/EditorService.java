package com.woniuxy.service;

import com.woniuxy.dto.Result;
import com.woniuxy.model.Book;
import com.woniuxy.model.BookData;
import com.woniuxy.model.Editor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.model.User;
import com.woniuxy.vo.EAuthorVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface EditorService extends IService<Editor> {
    public Editor login(Editor editor);

//    查询所有书籍
    public List<Book> findallBook();

//    查询作者
      public List<User> findallEditor();

//    查询签约作品
       public List<Book> findBook();

//    查看作品的数据
    public List<BookData> findallBookdata();

//    查询指定作者的信息
        public  List<EAuthorVo>  findBookDataStatus();
//      public List<EditorVo>  findUserPayment();

//    禁书
       public Integer findupdatbook(Integer bookid);
//    作品审核

      public Result insertAuthor();


}

