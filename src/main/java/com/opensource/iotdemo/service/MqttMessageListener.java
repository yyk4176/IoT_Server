package com.opensource.iotdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class MqttMessageListener {
    
    @Autowired
    MqttService mqttService;

    MqttReceiveCallBack mqttReceiveCallBack = new MqttReceiveCallBack();

    @PostConstruct
    public void subLockUpdate() {
        mqttService.subscribe("lock/updated", 1, mqttReceiveCallBack);
        System.out.println("订阅lock/updated成功");
    }

    @PostConstruct
    public void subLockCreate() {
        mqttService.subscribe("lock/created", 1, mqttReceiveCallBack);
        System.out.println("订阅lock/created成功");
    }

    @PostConstruct
    public void subLockDelete() {
        mqttService.subscribe("lock/deleted", 1, mqttReceiveCallBack);
        System.out.println("订阅lock/deleted成功");
    }
}
