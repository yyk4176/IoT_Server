package com.opensource.iotdemo.controller;

import com.opensource.iotdemo.entity.Lock;
import com.opensource.iotdemo.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/locks")
public class LockController {

    @Autowired
    private LockService lockService;

    @GetMapping
    public List<Lock> getAllLocks() {
        return lockService.getAllLocks();
    }

    @GetMapping("/{id}") 
    public Lock getLockById(@PathVariable Long id) {
        return lockService.getLockById(id);
    }

    @PostMapping
    public Lock createLock(@RequestBody Lock lock) {
        return lockService.createLock(lock);
    }

    @PutMapping("/{id}")
    public Lock updateLock(@PathVariable Long id, @RequestBody Lock lockDetails) {
        lockDetails.setId(id);
        return lockService.updateLock(id, lockDetails);
    }

    @PutMapping("/{id}/setstate/{state}")
    public Lock putMethodName(@PathVariable Long id, @PathVariable boolean state) {
        Lock lock = lockService.getLockById(id);
        lock.setStatus(state);
        lockService.updateLock(id, lock);
        return lock;
    }

    @DeleteMapping("/{id}")
    public void deleteLock(@PathVariable Long id) {
        lockService.deleteLock(id);
    }
}