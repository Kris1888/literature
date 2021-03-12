package com.woniuxy.service;

import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.woniuxy.model.Manager;
import com.baomidou.mybatisplus.extension.service.IService;
<<<<<<< Updated upstream
import com.woniuxy.model.User;
import com.woniuxy.vo.AllUserVo;
import com.woniuxy.vo.AuthorVO;
import org.apache.ibatis.annotations.Select;

import java.security.PrivateKey;
=======
import com.woniuxy.model.Application;
import com.woniuxy.model.Editor;
import com.woniuxy.model.Manager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.vo.*;

>>>>>>> Stashed changes
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
    //查询所有用户
     List<AllUserVo> findAllUser();
//查询所有书籍
    List<MBookVo> findAllBook();
//添加编辑
    boolean addEditor(Editor editor);

//查询所有作者
    List<MAuthorVO> findAllAuthor();
    boolean login(Manager manager);
    //查询所有编辑
    List<MEidtorVo> findAllEidtor();
    //修改编辑状态
    boolean updateEditor(String editorId);
    //封禁用户
    boolean banUser(String userId);
    //审核作者
    boolean checkAuthor(String userId);
    //签约作者
    boolean sginAuthor(String authorId);
    //禁书
    boolean banBook(String bookId);
    //审核书
    boolean checkBook(ApplicationVo applicationVo);
    //签约作品
    boolean signBook(ApplicationVo applicationVo);
    //管理员信息中心
    List<Application> getApplication();
    //查看合同
    List<ContractVo> getContract();




     List<AllUserVo> findAllUser();

    List<Book> findAllBook();

    boolean addEditor(Editor editor);
    boolean updateEditor(Editor editor);
    boolean updateUser(User user);
    boolean banBook(String bookname);

    List<AuthorVO> findAllAuthor();
    boolean login(Manager manager);

}
