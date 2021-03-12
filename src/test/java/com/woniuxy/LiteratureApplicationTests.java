package com.woniuxy;


import com.woniuxy.controller.EditorController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.woniuxy.service.BookService;
import com.woniuxy.service.CategoryService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LiteratureApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookService bookService;
    @Test
    void contextLoads() {


        EditorController editorController=new EditorController();
        editorController.findBook();

        //根据作者笔名模糊查询其所有作品
//       List<Book> books=bookMapper.selectPenNameAll("%"+"爱"+"%");
//        System.out.println(books);
        //根据书名模糊搜索
//        List<Book> bookList=bookMapper.selectBookNameFindAll("%"+"的"+"%");
//        System.out.println(bookList);
        //根据分类id查询该分类下的所有作品
//        List<Book> list=categoryMapper.selectCategoryByIdAndBook(1);
//        System.out.println(list);
        //查询所有分类
//        List<Category> categoryList= categoryService.findAll();
//        System.out.println(categoryList);
        //根据书籍id查询其详情
//        List<Book> bookList=bookMapper.selectBookIdfindAll(1);
//        System.out.println(bookList);
        //查询订阅数在前十的热门书籍
//        List<Book> list=bookMapper.selectBookDataANDdesc();
//        System.out.println(list);
        }

    }
