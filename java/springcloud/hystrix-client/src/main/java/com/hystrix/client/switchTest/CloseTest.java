package com.hystrix.client.switchTest;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;

public class CloseTest {
    public static void main(String[] args) throws Exception {
        //当断路器打开后，命令不再执行(一直触发回退)，这段时间为”休眠期”，默认为5秒；
        //当休眠期结束后，Hystrix会尝试执行一次命令，若命令执行成功，关闭断路器；若执行失败，继续保持打开断路器。

        //10秒内有三个请求满足开启断路器的第一个条件，默认10秒
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.metrics.rollingStats.timeInMilliseconds", 10000
        );
        //3个请求触发，默认20或10？
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.requestVolumeThreshold", 3
        );
        //请求失败50%出发，默认50%
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.errorThresholdPercentage", 50
        );
        //设置休眠期，默认5秒
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds", 3000
        );
        //该值决定是否执行超时
        boolean isTimeout = true;
        for (int i = 0; i < 10; i++){
            MyCommand command = new MyCommand(isTimeout);
            command.execute();
            //输出健康状态等信息
            HystrixCommandMetrics.HealthCounts healthCounts = command.getMetrics().getHealthCounts();
            System.out.println("断路器状态：" + command.isCircuitBreakerOpen() +
                    "，请求总数：" + healthCounts.getTotalRequests());
            if (command.isCircuitBreakerOpen()){
                //如果断路器打开
                isTimeout = false;
                System.out.println("=============断路器打开了，等待休眠期结束=========");
                //休眠期会在3秒结束，此处等待4秒，确保休眠期结束
                Thread.sleep(4000);
            }
        }
    }

    static class MyCommand extends HystrixCommand<String> {
        private boolean isTimeout;
        //设置超时时间为500毫秒,模拟超时响应
        public MyCommand(boolean isTimeout){
            super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionTimeoutInMilliseconds(500))
            );
            this.isTimeout = isTimeout;
        }

        protected String run() throws Exception{
            //让外部决定是否超时
            if(isTimeout){
                //模拟超时
                Thread.sleep(800);
            }else {
                Thread.sleep(200);
            }
            return "";
        }

        @Override
        protected String getFallback() {
            return "";
        }
    }
}
