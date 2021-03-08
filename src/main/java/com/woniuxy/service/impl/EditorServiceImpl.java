package com.woniuxy.service.impl;

import com.woniuxy.dto.Result;
import com.woniuxy.model.Book;
import com.woniuxy.model.Editor;
import com.woniuxy.mapper.EditorMapper;
import com.woniuxy.model.User;
import com.woniuxy.service.EditorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
   public String findallBookdata() {
      String s = editorMapper.findallBookdata();
      return s;
   }

   @Override
   public String findupdatbook() {
      String findupdatbook = editorMapper.findupdatbook();
      return findupdatbook;
   }

    @Override
    public Result insertAuthor() {

        return null;
    }
}
