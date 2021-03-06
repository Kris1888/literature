package com.woniuxy.service.impl;

import com.woniuxy.model.Book;
import com.woniuxy.mapper.BookMapper;
import com.woniuxy.service.BookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
