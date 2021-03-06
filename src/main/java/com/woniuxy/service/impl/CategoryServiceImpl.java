package com.woniuxy.service.impl;

import com.woniuxy.model.Category;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
