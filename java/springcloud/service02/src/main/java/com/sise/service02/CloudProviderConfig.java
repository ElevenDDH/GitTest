package com.sise.service02;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

//为服务提供者指定Ribbon配置类
@RibbonClient(name = "first-service-provider", configuration = MyConfig.class)
public class CloudProviderConfig {

}
