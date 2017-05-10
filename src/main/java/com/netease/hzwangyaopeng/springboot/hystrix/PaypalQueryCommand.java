package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netease.hzwangyaopeng.springboot.adapter.PaypalAdapter;
import com.netflix.hystrix.*;

/**
 *
 * command设置单独线程，并设置线程数为2
 * Created by hzwangyaopeng on 2017/5/9.
 */
public class PaypalQueryCommand extends HystrixCommand<String> {

    public PaypalQueryCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("paypal")).andCommandKey(HystrixCommandKey.Factory.asKey("paypal.query")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("paypal_query")).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(5)));
    }

    @Override
    protected String run() throws Exception {
        return PaypalAdapter.query();
    }

    @Override
    protected String getFallback(){
        return "当前系统繁忙，无法查询当前交易状态";
    }
}
