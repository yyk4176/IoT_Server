package com.opensource.iotdemo.service;

import com.opensource.iotdemo.entity.Lock;
import com.opensource.iotdemo.dao.LockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LockService {

    @Autowired
    private LockRepository lockRepository;

    @Autowired
    private MqttService mqttService;

    public List<Lock> getAllLocks() {
        return lockRepository.findAll();
    }

    public Lock getLockById(Long id) {
        return lockRepository.findById(id).orElse(null);
    }

    public Lock createLock(Lock lock) {
        Lock savedLock = lockRepository.save(lock);
        mqttService.publish("lock/created", 1, "Lock created: " + savedLock.toMessage());
        return savedLock;
    }

    public Lock updateLock(Long id, Lock lockDetails) {
        Lock lock = lockRepository.findById(id).orElse(null);
        if (lock != null) {
            lock.setId(lockDetails.getId());
            lock.setLockName(null != lockDetails.getLockName() ? lockDetails.getLockName() : lock.getLockName());
            lock.setStatus(lockDetails.getStatus());
            Lock updatedLock = lockRepository.save(lock);
            mqttService.publish("lock/updated", 1, "Lock updated: " + updatedLock.toMessage());
            return updatedLock;
        }
        return null;
    }

    public void deleteLock(Long id) {
        lockRepository.deleteById(id);
        mqttService.publish("lock/deleted", 1, "Lock deleted with ID: " + id);
    }
}
