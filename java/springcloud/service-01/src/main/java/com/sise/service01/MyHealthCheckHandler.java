package com.sise.service01;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

//实现健康检查处理器，将服务健康状态告诉服务器
//一旦状态发生改变，重新向服务器注册。
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {
    @Autowired
    private MyHealthIndicator indicator;
    @Override
    public InstanceStatus getStatus(InstanceStatus instanceStatus) {
        Status status = indicator.health().getStatus();
        if (status.equals(Status.UP)){
            System.out.println("数据库连接正常");
            return InstanceStatus.UP;
        }else {
            System.out.println("数据库无法连接");
            return InstanceStatus.DOWN;
        }
    }
}
