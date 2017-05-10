package com.netease.hzwangyaopeng.springboot.controller;

import com.netease.hzwangyaopeng.springboot.hystrix.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzwangyaopeng on 2017/3/27.
 */
@RestController
public class PayController {


    @RequestMapping("/alipay/pay")
    public String alipayPay(HttpServletRequest request,HttpServletResponse response) throws IOException {
        long begin=System.currentTimeMillis();
        String result=new AlipayPayCommand().execute();
        System.err.println(System.currentTimeMillis()-begin);
        return result;
    }

    @RequestMapping("/alipay/query")
    public String alipayQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return new AlipayQueryCommand().execute();
    }

    @RequestMapping("/alipay/withdraw")
    public String alipayWithdraw(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return new AlipayWithdrawCommand().execute();
    }

    @RequestMapping("/paypal/pay")
    public String paypalPay(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return new PaypalPayCommand().execute();
    }

    @RequestMapping("/paypal/query")
    public String paypalQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return new PaypalQueryCommand().execute();
    }

    @RequestMapping("/paypal/withdraw")
    public String paypalWithdraw(HttpServletRequest request,HttpServletResponse response) throws IOException {
        return new PaypalWithdrawCommand().execute();
    }
}
