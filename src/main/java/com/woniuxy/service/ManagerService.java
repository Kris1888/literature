package com.woniuxy.service;

import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.woniuxy.model.Manager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.model.User;
import com.woniuxy.vo.AllUserVo;
import com.woniuxy.vo.AuthorVO;
import org.apache.ibatis.annotations.Select;

import java.security.PrivateKey;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface ManagerService extends IService<Manager> {

     List<AllUserVo> findAllUser();

    List<Book> findAllBook();

    boolean addEditor(Editor editor);
    boolean updateEditor(Editor editor);
    boolean updateUser(User user);
    boolean banBook(String bookname);

    List<AuthorVO> findAllAuthor();
    boolean login(Manager manager);

}
