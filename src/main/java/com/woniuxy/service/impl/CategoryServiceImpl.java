package com.woniuxy.service.impl;

import com.woniuxy.model.Category;
import com.woniuxy.mapper.CategoryMapper;
import com.woniuxy.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
  @Resource
  private CategoryMapper categoryMapper;
//  @Resource
//  private RedisTemplate<Object,Object>redisTemplate;
  //查询所有分类
    @Override
    public List<Category> findAll() {
      List<Category> categories=categoryMapper.selectList(null);
        return categories;
    }
}


