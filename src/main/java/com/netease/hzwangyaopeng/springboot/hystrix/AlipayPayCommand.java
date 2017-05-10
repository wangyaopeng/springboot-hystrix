package com.netease.hzwangyaopeng.springboot.hystrix;

import com.netease.hzwangyaopeng.springboot.adapter.AlipayAdapter;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * group为alipay，即使用线程池为alipay，command为pay
 * Created by hzwangyaopeng on 2017/3/29.
 */
public class AlipayPayCommand extends HystrixCommand<String>{



    public AlipayPayCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("alipay")).andCommandKey(HystrixCommandKey.Factory.asKey("alipay.pay")));
    }

    @Override
    protected String run() throws Exception {
        try {
            return AlipayAdapter.pay();

        }catch (Exception e){
            System.err.println(e.getMessage());
//                throw e;
            return null;
        }
    }




}
