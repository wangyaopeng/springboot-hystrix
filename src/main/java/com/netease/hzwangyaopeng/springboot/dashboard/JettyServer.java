package com.netease.hzwangyaopeng.springboot.dashboard;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hzwangyaopeng on 2017/5/10.
 */
public class JettyServer {
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Server server = null;
    public void init() {
        try {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //绑定8080端口,加载HystrixMetricsStreamServlet并映射url

                        server = new Server(80);
                        WebAppContext context = new WebAppContext();
                        context.setContextPath("/");
                        context.addServlet(HystrixMetricsStreamServlet.class, "/hystrix.stream");
                        context.setResourceBase(".");
                        server.setHandler(context);
                        server.start();
                        server.join();
                    } catch (Exception e) {
                        System.err.println( e);
                    }
                }
            });
        } catch (Exception e) {
            System.err.println( e);
        }
    }
    public void destory() {
        if (server != null) {
            try {
                server.stop();
                server.destroy();

                System.err.println("jettyServer stop and destroy!");
            } catch (Exception e) {
                System.err.println( e);
            }
        }
    }

    public static  void main(String[]args){
        JettyServer jettyServer=new JettyServer();
        jettyServer.init();
    }
}