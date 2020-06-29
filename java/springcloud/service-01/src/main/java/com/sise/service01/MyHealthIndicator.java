package com.sise.service01;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

//健康指示器
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {//判断健康状态
        if (HealthController.canVisitDb){
            return new Health.Builder(Status.UP).build();
        }else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
