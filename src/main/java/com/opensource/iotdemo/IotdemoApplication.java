package com.opensource.iotdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.opensource.iotdemo.config.MqttProperties;

@SpringBootApplication
@EnableConfigurationProperties(MqttProperties.class)
@EnableScheduling
public class IotdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotdemoApplication.class, args);
	}

}
