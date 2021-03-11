package com.woniuxy.service.impl;

import com.woniuxy.dto.Result;
import com.woniuxy.model.Book;
import com.woniuxy.model.BookData;
import com.woniuxy.model.Editor;
import com.woniuxy.mapper.EditorMapper;
import com.woniuxy.model.User;
import com.woniuxy.service.EditorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.EAuthorVo;
import com.woniuxy.vo.EditorVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class EditorServiceImpl extends ServiceImpl<EditorMapper, Editor> implements EditorService {

    @Resource
    EditorMapper editorMapper;


    @Override
    public Editor login(Editor editor) {
        Editor user1 = editorMapper.login(null);
        return user1;
    }

    @Override
    public List<Book> findallBook() {
        List<Book> books = editorMapper.findallBook();
        return books;
    }

    @Override
    public List<User> findallEditor() {
        List<User> users = editorMapper.findallEditor();
        return users;
    }

    @Override
    public List<Book> findBook() {
        List<Book> book = editorMapper.findBook();
        return book;
    }

    @Override
       public List<BookData> findallBookdata(){
        List<BookData> bookData = editorMapper.findallBookdata();
        return bookData;
    }

    @Override
    public List<EAuthorVo> findBookDataStatus() {
//        查询t_book的数据
        System.out.println("进入查book");
        List<EAuthorVo> bookDataStatus = editorMapper.findBookDataStatus();
        System.out.println(bookDataStatus);
//查询t_user数据
        System.out.println("进入查user");
        List<EditorVo> userPayment = editorMapper.findUserPayment();
        System.out.println(userPayment);

//        遍历s
        for (EAuthorVo f : bookDataStatus) {
            String userId = f.getUserId();
            System.out.println("f"+f);
            for (EditorVo m : userPayment) {
                System.out.println("m"+m);
                if (userId == (m.getAuthorid())) {
                    f.setAuthorName(m.getUsername());
                    f.setPayment(m.getPayment());
                    break;
                }
            }
        }
        return bookDataStatus;
    }



    @Override
    public Integer findupdatbook(Integer bookid) {
        Integer findupdatbook = editorMapper.findupdatbook();
            return findupdatbook;
    }

    @Override
    public Result insertAuthor() {
        return null;
    }

}
//