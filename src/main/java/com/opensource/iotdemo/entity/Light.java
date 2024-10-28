package com.opensource.iotdemo.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`light`")
public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`light_name`", nullable = false)
    private String lightName;

    @Column(name = "`status`", nullable = false)
    private boolean status; // true = on, false = off

    public String toMessage(){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> message = new HashMap<>();
        message.put("deviceId", id);
        message.put("lightName", lightName);
        message.put("status", status ? "on" : "off");
        message.put("timestamp", System.currentTimeMillis());
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Light() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLightName() {
        return lightName;
    }

    public void setLightName(String lockId) {
        this.lightName = lockId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean locked) {
        this.status = locked;
    }
}
