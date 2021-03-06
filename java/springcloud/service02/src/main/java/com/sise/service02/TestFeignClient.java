package com.sise.service02;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

public class TestFeignClient {
    public static void main(String[] args) throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = loggerContext.getLogger("root");
        logger.setLevel(Level.toLevel("INFO"));
        //创建默认HttpClient
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        //调用多次服务并输出结果
        for (int i = 0; i < 6; i++){
            //建立线程访问接口
            Thread thread = new Thread(){
                public void run(){
                    try {
                        String url = "http://localhost:9000/feign/hello";
                        //调用Get方法请求服务
                        HttpGet httpGet = new HttpGet(url);
                        //获取响应
                        HttpResponse response = httpClient.execute(httpGet);
                        //根据响应解析字符串
                        System.out.println(EntityUtils.toString(response.getEntity()));
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
        //等待完成
        Thread.sleep(15000);
    }
}
