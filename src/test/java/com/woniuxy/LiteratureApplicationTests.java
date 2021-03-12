package com.woniuxy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.mapper.CollectionMapper;
import com.woniuxy.mapper.SubscribeMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.woniuxy.model.Collection;
import com.woniuxy.model.Subscribe;
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
    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private SubscribeMapper subscribeMapper;
    @Test
    void contextLoads() {
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
//        String aaa = "{\"params\":{\"bookifname\":\"大国\"}}";
//        System.out.println(aaa);
//        String bookName = aaa.substring(25);
//        System.out.println(bookName);
//        String bookName2 = bookName.substring(0,bookName.indexOf("\""));
//        System.out.println(bookName2);
//        String co = "{\"params\":{\"userId\":\"20\",\"bookId\":12}}";
//        String cc = co.substring(21);
//        String coo = co.substring(34);
//        String userId = cc.substring(0,cc.indexOf("\""));
//        String bookId = coo.substring(0,coo.indexOf("}"));
        //1
//        collectionMapper.INSERTCOLLECTION(1,1);
        subscribeMapper.INSETTsuBCRIBE(1,150,1);

        }
    }
