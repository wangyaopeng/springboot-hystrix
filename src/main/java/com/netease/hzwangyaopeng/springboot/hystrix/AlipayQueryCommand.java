package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netflix.hystrix.*;

/**
 *
 * command设置单独线程，并设置线程数为2
 * Created by hzwangyaopeng on 2017/5/9.
 */
public class AlipayQueryCommand extends HystrixCommand<String> {

    public AlipayQueryCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("alipay")).andCommandKey(HystrixCommandKey.Factory.asKey("alipay.query")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("alipay_query")).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2)));
    }

    @Override
    protected String run() throws Exception {
        return AlipayAdapter.query();
    }

    @Override
    protected String getFallback(){
        return "当前系统繁忙，无法查询当前交易状态";
    }
}
