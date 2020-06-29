package com.sise.client;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class ChooseServerTest {
    public static void main(String[] args) {
        //ILoadBalancer用于服务器的选择，可以使用实现类BaseLoadBalancer
        //ILoadBalancer iLoadBalancer = new BaseLoadBalancer();//创建负载均衡器
        //设置自定义负载规则
        BaseLoadBalancer iLoadBalancer = new BaseLoadBalancer();
        iLoadBalancer.setRule(new MyRule(iLoadBalancer));

        List<Server>servers = new ArrayList<Server>();
        //添加服务器
        servers.add(new Server("localhost", 8081));
        servers.add(new Server("localhost", 8085));
        iLoadBalancer.addServers(servers);
        for (int i = 0; i < 6; i++){
            Server server = iLoadBalancer.chooseServer(null);
            System.out.println(server);
        }
    }
}
