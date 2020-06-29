package com.sise.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Receive {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        String queueName = "hello";
        channel.queueDeclare(queueName, false, false, false, null);
        //创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("接收得消息：" + message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
