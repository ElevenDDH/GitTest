package com.sise.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

@SuppressWarnings("deprecation")
public class RibbonClientApplication {
    public static void main(String[] args)throws Exception {
        //设置请求服务器
//        ConfigurationManager.getConfigInstance().setProperty(
//                "my-client.ribbon.listOfServers",
//                "localhost:8081, localhost:8085"
//        );
        //设置读取配置文件请求服务器
        ConfigurationManager.loadPropertiesFromResources("application.properties");

        RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
        HttpRequest request = HttpRequest.newBuilder().uri("/person/1").build();
        //获取REST客户端及创建请求实例
        for (int i = 0; i < 10; i++){
            HttpResponse response = client.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println(result);
        }
    }
}
