package com.woniuxy.pay;

import com.alipay.api.AlipayApiException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.User;
import com.woniuxy.util.UUIDUtil;
import com.woniuxy.vo.PayInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 支付宝沙箱测试
 *
 * @author 小道仙
 * @date 2020年2月17日
 */
@RestController
@RequestMapping("/order")
public class PayController {
    @Resource
    UserMapper userMapper;
    @Autowired
    private PayService payService;

    /**
     * 阿里支付
     * @param tradeNo
     * @param subject
     * @param amount
     * @param body
     * @return
     * @throws AlipayApiException
     */
    @PostMapping(value = "alipay")
    public String alipay(@RequestBody PayInfoVO payInfoVO) throws AlipayApiException {

        AlipayBean alipayBean = new AlipayBean();
//        alipayBean.setOut_trade_no(payInfoVO.getOutTradeNo());
//        alipayBean.setSubject(payInfoVO.getSubject());
        String Out_trade_no=UUIDUtil.getUUID().replace("-", "");
        alipayBean.setOut_trade_no(Out_trade_no);
        String Subject=UUIDUtil.getUUID().replace("-", "");
        alipayBean.setSubject(Subject);
        alipayBean.setTotal_amount(payInfoVO.getTotalAmount());
      alipayBean.setBody(payInfoVO.getBody());
        String Body=UUIDUtil.getUUID().replace("-", "");
        alipayBean.setBody(Body);
//        System.out.println(Out_trade_no);
//        System.out.println(Subject);
//        System.out.println(Body);
     return payService.aliPay(alipayBean);
//       Integer TotalAmount= payInfoVO.getTotalAmount();
//        return TotalAmount;
    }
    @GetMapping("RefreshMoney/{totalAmount}/{uid}")
    public Result RefreshMoney(@PathVariable Integer totalAmount,@PathVariable Integer uid){
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("user_id",uid);
        User userDB = userMapper.selectOne(queryWrapper);
        Integer account=userDB.getAccount();
       account=account+totalAmount;
       UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
       userUpdateWrapper.eq("t_user.user_id",uid);
       userUpdateWrapper.set("account",account);
       userMapper.update(userDB,userUpdateWrapper);
        return new Result(true, StatusCode.OK,"充值成功",totalAmount);
    }

}
