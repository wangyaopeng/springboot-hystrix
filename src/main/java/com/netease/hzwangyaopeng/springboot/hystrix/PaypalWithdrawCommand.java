package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netease.hzwangyaopeng.springboot.adapter.PaypalAdapter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * Created by hzwangyaopeng on 2017/5/10.
 */
public class PaypalWithdrawCommand extends HystrixCommand<String> {
//    .andCommandKey(HystrixCommandKey.Factory.asKey("withdraw"))
    public PaypalWithdrawCommand(){
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("paypal")).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(50)));
    }

    @Override
    protected String run() throws Exception {
        return PaypalAdapter.withdraw();
    }

    @Override
    protected String getFallback(){
        return "当前系统繁忙，无法退款";
    }

}
