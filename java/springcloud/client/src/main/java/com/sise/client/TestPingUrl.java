package com.sise.client;

import com.netflix.client.ClientFactory;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

import java.io.IOException;
import java.util.List;

public class TestPingUrl {
    public static void main(String[] args) throws InterruptedException, IOException {
        //普通方法
//        BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer();
//        List<Server> servers = new ArrayList<Server>();
//        servers.add(new Server("localhost", 8081));
//        servers.add(new Server("localhost", 8088));
//        baseLoadBalancer.addServers(servers);
//        //设置IPing的实现类PingUrl及时间间隔
//        baseLoadBalancer.setPing(new PingUrl());
//        baseLoadBalancer.setPingInterval(2);
//
//        Thread.sleep(6000);
//        for (Server server:baseLoadBalancer.getAllServers()){
//            System.out.println(server.getHostPort()+" State:"+server.isAlive());
//        }
        //配置文件方法
        ConfigurationManager.loadPropertiesFromResources("application.properties");
        RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
        Thread.sleep(6000);
        List<Server> servers = client.getLoadBalancer().getAllServers();
        System.out.println(servers.size());
        for (Server server:servers){
            System.out.println(server.getHostPort()+"=============State:"+server.isAlive());
        }
    }
}
