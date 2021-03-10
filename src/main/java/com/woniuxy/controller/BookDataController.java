package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.service.BookDataService;
import com.woniuxy.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/book-data")
public class BookDataController {
    @Resource
    private BookService bookService;
    @Resource
    private BookDataService bookDataService;
    //热门书籍展示
    @GetMapping("/RebookandByAll")
    @ResponseBody
    public Result selectBookandAll(){
//        QueryWrapper<>
        return new Result(true, StatusCode.OK,"查寻热门书籍成功");
    }

}

