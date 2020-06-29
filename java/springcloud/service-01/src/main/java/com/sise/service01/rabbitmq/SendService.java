package com.sise.service01.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface SendService {
    //@Output表示创建myInput的消息通道
    @Output("myInput")
    SubscribableChannel sendOrder();
}
