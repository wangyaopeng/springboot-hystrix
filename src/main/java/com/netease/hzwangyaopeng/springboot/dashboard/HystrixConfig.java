package com.netease.hzwangyaopeng.springboot.dashboard;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.turbine.streaming.servlet.TurbineStreamServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hzwangyaopeng on 2017/5/9.
 */
@Configuration
public class HystrixConfig {

    @Bean
    public HystrixMetricsStreamServlet hystrixMetricsStreamServlet() {
        return new HystrixMetricsStreamServlet();
    }

    @Bean
    public ServletRegistrationBean registration(HystrixMetricsStreamServlet servlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(servlet);
        registrationBean.setEnabled(true);//是否启用该registrationBean
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }

//    @Bean
//    public TurbineStreamServlet turbineStreamServlet() {
//        return new TurbineStreamServlet();
//    }
//
//    @Bean
//    public ServletRegistrationBean registration(TurbineStreamServlet servlet) {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
//        registrationBean.setServlet(servlet);
//        registrationBean.setEnabled(true);//是否启用该registrationBean
//        registrationBean.addUrlMappings("/turbine.stream");
//        return registrationBean;
//    }

}
