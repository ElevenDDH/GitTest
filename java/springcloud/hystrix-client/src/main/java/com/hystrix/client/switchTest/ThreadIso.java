package com.hystrix.client.switchTest;

import com.netflix.config.ConfigurationManager;

//线程隔离策略示例
public class ThreadIso {
    public static void main(String[] args) throws Exception {
        //配置线程池大小为3
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.threadpool.default.coreSize", 3
        );
        for (int i = 0; i < 6; i++){
            MyCommand command = new MyCommand(i);
            command.queue();
        }
        Thread.sleep(5000);
    }

}
