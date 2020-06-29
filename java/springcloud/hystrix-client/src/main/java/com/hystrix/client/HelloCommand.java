package com.hystrix.client;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HelloCommand extends HystrixCommand<String> {
    private String url;
    CloseableHttpClient httpClient;

    public HelloCommand(String url){
        //调用父类构造方法，设置命令组的key，默认用来作为线程池的key
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        //创建HttpClient客户端
        this.httpClient = HttpClients.createDefault();
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        try {
            //调用Get方法请求服务
            HttpGet httpGet = new HttpGet(url);
            //获取服务响应
            HttpResponse response = httpClient.execute(httpGet);
            //解析并返回命令执行的结果
            return EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    //在请求命令中增加回退方法
    protected String getFallback(){
        System.out.println("执行HelloCommand的回退");
        return "error";
    }

}
