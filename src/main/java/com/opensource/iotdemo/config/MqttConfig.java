package com.opensource.iotdemo.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqttConfig {

    @Autowired
    @Bean
    public MqttClient mqttClient(MqttProperties mqttProperties) throws MqttException {
        MqttClient client = new MqttClient(mqttProperties.getBrokerUrl(), mqttProperties.getClientId());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        return client;
    }
    
}