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
@Table(name = "`lock`")
public class Lock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`lock_name`", nullable = false)
    private String lockName;

    @Column(name = "`status`", nullable = false)
    private boolean status; // true = locked, false = unlocked

    public String toMessage(){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> message = new HashMap<>();
        message.put("deviceId", id);
        message.put("lockName", lockName);
        message.put("status", status ? "locked" : "unlocked");
        message.put("timestamp", System.currentTimeMillis());
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lock() {
    }

    public Lock(String lockId, boolean isLocked) {
        this.lockName = lockId;
        this.status = isLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockId) {
        this.lockName = lockId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean locked) {
        this.status = locked;
    }

}
