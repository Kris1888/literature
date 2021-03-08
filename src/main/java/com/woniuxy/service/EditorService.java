package com.woniuxy.service;

import com.woniuxy.dto.Result;
import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.model.User;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
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

//    查询所有书籍
    public List<Book> findallBook();

//    查询作者
      public List<User> findallEditor();

//    查询签约作品

//    查看作品的数据
       public String findallBookdata();

//    查询指定作者的信息

//    禁书
       public String findupdatbook();
//    作品审核

      public Result insertAuthor();
}

