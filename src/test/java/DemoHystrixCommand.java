import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hzwangyaopeng on 2017/5/9.
 */
public class DemoHystrixCommand extends HystrixCommand<String> {

    public DemoHystrixCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(1000);
        return "run";
    }

    protected String getFallback() {
        return "exeucute Falled";
    }

    public static void main(String[]args) throws InterruptedException, ExecutionException, TimeoutException {

        DemoHystrixCommand demo=new DemoHystrixCommand();
        System.out.println(demo.execute());
        System.out.println(demo.queue().get(100, TimeUnit.MILLISECONDS));
    }
}
