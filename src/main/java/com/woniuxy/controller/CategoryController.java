package com.woniuxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.woniuxy.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private CategoryService categoryService;
    //查询首页导航栏分类
    @GetMapping("/CategoryAll")
    @ResponseBody
    public Result selectCategoryAll(){
        return new Result(true, StatusCode.OK,"查询所有分类成功",categoryService.findAll());
    }





}

