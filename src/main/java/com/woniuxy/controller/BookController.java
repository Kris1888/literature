package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.annotations.VisibleForTesting;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.model.Book;
import com.woniuxy.service.BookService;
import com.woniuxy.vo.PageVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookService bookService;
    //根据分类id查询分类下的所有书籍
    @GetMapping("/selectCategoryByid")
    public Result selectCategoryByIdANDbook(Integer id) {
        System.out.println(1);
        System.out.println(id);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", id);
        List<Book> books = bookService.list(queryWrapper);
        if (!ObjectUtils.isEmpty(books)) {
            return new Result(true, StatusCode.OK, "查询分类下所有书籍成功", books);
        } else {
            return new Result(false, StatusCode.CHAXUNWEIKONG, "查询分类下所有书籍失败");
        }
    }

    //根据书籍id查询书籍所有信息
    @GetMapping("/bookAll")
    public Result selectBookByIdAndAll(@RequestBody Integer book_id){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",book_id);
        List<Book> books=bookService.list(queryWrapper);
        return new Result(true, StatusCode.OK,"查新书籍详情成功",books);
    }
    //分类书籍分页查询
    @GetMapping("/getAll")
    public Result find2Page(PageVO pageVO){
        System.out.println(pageVO);
        //设置分页查询条件
        Page<Book> bookPage=new Page<>(pageVO.getCurrent(),pageVO.getSize());
        IPage<Book> page=bookService.page(bookPage,null);
        return new Result(true,StatusCode.OK,"用户信息分页查询成功",page);
    }
    //根据书名模糊搜索
    @GetMapping("/categoryByName")
    public Result selectCategoryByName(@RequestBody Book book){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(book.getBook_name()),"%"+"book_name"+"%",book.getBook_name());
        List<Book> bookList=bookMapper.selectList(queryWrapper);
        return new Result(true,StatusCode.OK,"根据书名模糊查询成功",bookList);
    }
    //根据作者笔名模糊搜索其所有作品
    @GetMapping("/bookByPenName")
    public Result selectBookByPenName(@RequestBody Book book){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(book.getUser().getPen_name()),"%"+"pen_name"+"%",book.getUser().getPen_name());
        List<Book> bookList1=bookMapper.selectList(queryWrapper);
        return new Result(true,StatusCode.OK,"根据作者笔名模糊搜索其所有作品成功",bookList1);
    }

}

