package com.hystrix.client.test;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

//利用Hystrix设计触发回退，满足以下条件：[0,30),让小于等于10的偶数可以正常执行，让奇数或大于10的数进入fallback。
public class FallbackTest01 extends HystrixCommand<String> {
    private Integer id;

    public FallbackTest01(){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }
    public FallbackTest01(Integer id){
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
//        if (id % 2 == 0 && id <= 10){
//            return "running run(): " + id;
//        }else {
//            //让奇数或大于10的数进入fallback
//            TimeUnit.MILLISECONDS.sleep(2000);
//            return "";
//        }

        //2.1
        System.out.println("断路器测试Run()");
        return "";

    }

    @Override
    protected String getFallback(){
        System.out.println("running Fallback(): " + id);
        return "";
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 30; i++){
//            System.out.println(new FallbackTest01(i).execute());
//        }


        //2.1
        //测试一下断路器被打开时的回退
        //断路器被强制打开
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.forceOpen", "true"
        );
        FallbackTest01 command = new FallbackTest01();
        command.execute();

        //断路器关闭
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.forceOpen", "false"
        );
        command = new FallbackTest01();
        command.execute();

    }
}
