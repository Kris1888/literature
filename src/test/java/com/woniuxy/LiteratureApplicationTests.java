package com.woniuxy;

import com.woniuxy.mapper.BookMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.woniuxy.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LiteratureApplicationTests {
    @Resource
    private CategoryService categoryService;
    @Resource
    private BookMapper bookMapper;
    @Test
    void contextLoads() {
//        List<Category> categoryList= categoryService.findAll();
//        System.out.println(categoryList);
        List<Book> books=bookMapper.selectPenNameAll("%"+"爱"+"%");
        System.out.println(books);
//        List<Category> categoryList= categoryService.findAll();
//        System.out.println(categoryList);
    }

}
