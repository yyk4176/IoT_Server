package com.opensource.iotdemo.service;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttService {

    @Autowired
    MqttClient mqttClient;

    public void publish(String topic, int Qos, String message) { // mqtt推送消息
        MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
        try{
            mqttClient.publish(topic, mqttMessage.getPayload(), Qos, false);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    public void subscribe(String topicName, int qos) { // mqtt订阅消息
        try {
            this.mqttClient.subscribe(topicName, qos);
            
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topicName, int qos, IMqttMessageListener messageListener) { 
        // mqtt订阅消息（带消息监听器）
        try {
            this.mqttClient.subscribe(topicName, qos, messageListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void cleanTopic(String topicName) { // 清除订阅
        try {
            this.mqttClient.unsubscribe(topicName);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
