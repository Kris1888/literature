package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.woniuxy.dto.Result;
import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.woniuxy.model.Manager;
import com.woniuxy.mapper.ManagerMapper;
import com.woniuxy.model.User;
import com.woniuxy.service.BookService;
import com.woniuxy.service.EditorService;
import com.woniuxy.service.ManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.service.UserService;
import com.woniuxy.vo.AllUserVo;
import com.woniuxy.vo.AuthorVO;
import com.woniuxy.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;
    @Resource
    private EditorService editorService;
    @Resource
    private ManagerMapper managerMapper;

    @Override
    //查询所有用户
    public List<AllUserVo> findAllUser() {
        List<AllUserVo> list = managerMapper.findAllUser();
        return list;
    }

    @Override
    //查询所有作者
    public List<AuthorVO> findAllAuthor() {
        List<AuthorVO> allAuthor = managerMapper.findAllAuthor();
        return allAuthor;
    }

    @Override
    public List<Book> findAllBook() {
        List<Book> list = bookService.list(null);
        return list;
    }

    @Override
    public boolean addEditor(Editor editor) {
        boolean save = editorService.save(editor);
        return save;
    }

    @Override
    public boolean updateEditor(Editor editor) {
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public boolean banBook(String bookname) {
        return true;
    }


//登陆方法
    @Override
    public boolean login(Manager manager) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        managerQueryWrapper.eq("manager_tel",manager.getManagerTel());
        Manager one = managerMapper.selectOne(managerQueryWrapper);
        System.out.println(one);
        if (!ObjectUtils.isEmpty(one)) {
            if (manager.getPassword().equals(one.getPassword())){
                System.out.println("登陆成功");
                return true;
            }

        }
        System.out.println("登陆失败");
        return false;

    }
}
