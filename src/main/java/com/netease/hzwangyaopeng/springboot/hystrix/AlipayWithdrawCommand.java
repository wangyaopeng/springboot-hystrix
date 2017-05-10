package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * Created by hzwangyaopeng on 2017/5/9.
 */
public class AlipayWithdrawCommand extends HystrixCommand<String>{

    public AlipayWithdrawCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("alipay")).andCommandKey(HystrixCommandKey.Factory.asKey("alipay.withdraw")));
    }

    @Override
    protected String run() throws Exception {
        return AlipayAdapter.withdraw();
    }

    @Override
    protected String getFallback(){
        return "当前系统繁忙，无法退款";
    }
}
