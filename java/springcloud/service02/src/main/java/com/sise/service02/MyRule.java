package com.sise.service02;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule {
    ILoadBalancer iLoadBalancer;
    public  MyRule(){}
    public MyRule(ILoadBalancer iLoadBalancer){
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public Server choose(Object o) {//决定选择哪一个服务器对象
        List<Server> servers = iLoadBalancer.getAllServers();
        //按比例选择端口
        Random random = new Random();
        final int number = random.nextInt(10);

        System.out.println("这是自定义服务器规则类， 输出服务器信息：");
        for (Server s:servers){
            System.out.println("           "+s.getHostPort());
        }


        if (number < 6){
            return findServer(servers, 8082);
        }
        return findServer(servers, 8085);

        //返回第一个
        //return servers.get(0);

    }
    public Server findServer(List<Server> allServers, int port){
        for (Server server : allServers){
            //可加ping处理端口是否正常，不正常交另外一个端口处理
            if (server.getPort() == port){
                return server;
            }
        }
        System.out.println("NULL port = "+port);
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.iLoadBalancer;
    }
}
