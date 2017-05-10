package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netease.hzwangyaopeng.springboot.adapter.PaypalAdapter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 使用信号量隔离
 * group为alipay，command为pay
 * Created by hzwangyaopeng on 2017/3/29.
 */
public class PaypalPayCommand extends HystrixCommand<String>{



    public PaypalPayCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("paypal")).andCommandKey(HystrixCommandKey.Factory.asKey("paypal.pay")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
    }

    @Override
    protected String run() throws Exception {
        return PaypalAdapter.pay();
    }




}
