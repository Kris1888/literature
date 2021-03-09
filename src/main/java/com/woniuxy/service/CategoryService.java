package com.woniuxy.service;

import com.woniuxy.model.Book;
import com.woniuxy.model.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface CategoryService extends IService<Category> {

    List<Category>findAll();
}
