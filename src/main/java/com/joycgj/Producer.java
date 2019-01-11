package com.joycgj;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("chengaojie");
        factory.setPassword("123456");

        // 设置 RabbitMQ 地址
        factory.setHost("10.179.39.109");
        factory.setPort(5672);

        // 建立到代理服务器到连接
        Connection conn = factory.newConnection();

        // 获得信道
        Channel channel = conn.createChannel();

        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        String routingKey = "hola";

        // 发布消息
        byte[] messageBodyBytes = "hello world".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        channel.close();
        conn.close();
    }
}
