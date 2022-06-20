package com.xingyu.common.rabbit.service;

public interface RabbitService {
    /**
     *  发送消息
     * @param exchange 交换机
     * @param routingKey 路由键
     * @param message 消息
     */
    boolean sendMessage(String exchange, String routingKey, Object message);
}
