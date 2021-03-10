package com.woniuxy.service.impl;

import com.woniuxy.model.Message;
import com.woniuxy.mapper.MessageMapper;
import com.woniuxy.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author migua
 * @since 2021-03-10
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
