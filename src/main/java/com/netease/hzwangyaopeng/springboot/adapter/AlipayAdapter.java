package com.netease.hzwangyaopeng.springboot.adapter;

/**
 * 远程系统支付宝adapter
 * Created by hzwangyaopeng on 2017/5/9.
 */
public class AlipayAdapter {

public static String pay(){
    return "execute alipay.pay";
}

public static String query(){
    return "execute alipay.query";

}

public static String withdraw(){
    if(System.currentTimeMillis()%2==0){
        throw new RuntimeException("execute alipay.withdraw error");
    }


    return "execute alipay.withdraw";
}

}
