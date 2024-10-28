package com.opensource.iotdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.opensource.iotdemo.entity.Light;

@Repository
public interface LightRepository extends JpaRepository<Light, Long>{
    
}
