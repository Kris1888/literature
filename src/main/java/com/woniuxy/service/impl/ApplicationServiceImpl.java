package com.woniuxy.service.impl;

import com.woniuxy.model.Application;
import com.woniuxy.mapper.ApplicationMapper;
import com.woniuxy.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-11
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}
