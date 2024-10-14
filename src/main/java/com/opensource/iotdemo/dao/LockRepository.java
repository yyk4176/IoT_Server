package com.opensource.iotdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.opensource.iotdemo.entity.Lock;

@Repository
public interface LockRepository extends JpaRepository<Lock, Long>{
    
}
