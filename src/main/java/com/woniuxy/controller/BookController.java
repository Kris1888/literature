package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.model.Book;
import com.woniuxy.service.BookService;
import com.woniuxy.vo.PageVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    //根据书籍id查询书籍所有信息
    @GetMapping("/bookAll")
    public Result selectBookByIdAndAll(@RequestBody String book_id){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",book_id);
        List<Book> books=bookService.list(queryWrapper);
        return new Result(true, StatusCode.OK,"查新书籍详情成功",books);
    }
    //分类书籍分页查询
    @GetMapping("getAll")
    public Result find2Page(PageVO pageVO){
        System.out.println(pageVO);
        //设置分页查询条件
        Page<Book> bookPage=new Page<>(pageVO.getCurrent(),pageVO.getSize());
        IPage<Book> page=bookService.page(bookPage,null);
        return new Result(true,StatusCode.OK,"用户信息分页查询成功",page);
    }
}

