package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.woniuxy.service.CategoryService;
import org.springframework.util.ObjectUtils;
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
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private CategoryService categoryService;
    //查询首页导航栏分类信息
    @GetMapping("/CategoryAll")
    public Result selectCategoryAll(){
        return new Result(true, StatusCode.OK,"查询所有分类成功",categoryService.);
    }

    //根据分类id查询分类下的所有书籍
    @GetMapping("/CategoryByIdANDbook")
    public Result selectCategoryByIdANDbook(@RequestBody Category category){
            QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("category_id",category.getCategoryId());
            List<Category> categories= categoryService.list(queryWrapper);
            if (!ObjectUtils.isEmpty(categories)){
                return new Result(true,StatusCode.OK,"查询分类下所有书籍成功",categories);
            }else {
                return new Result(false,StatusCode.ERROR,"查询分类下所有书籍失败");
            }

    }

}

