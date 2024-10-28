package com.opensource.iotdemo.controller;

import com.opensource.iotdemo.entity.Light;
import com.opensource.iotdemo.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lights")
public class LightController {

    @Autowired
    private LightService lightService;

    @GetMapping
    public List<Light> getAllLocks() {
        return lightService.getAllLights();
    }

    @GetMapping("/{id}") 
    public Light getLightById(@PathVariable Long id) {
        return lightService.getLightById(id);
    }

    @PostMapping
    public Light createLight(@RequestBody Light light) {
        return lightService.createLight(light);
    }

    @PutMapping("/{id}")
    public Light updateLight(@PathVariable Long id, @RequestBody Light lightDetails) {
        lightDetails.setId(id);
        return lightService.updateLight(id, lightDetails);
    }

    @PutMapping("/{id}/setstate/{state}")
    public Light changeState(@PathVariable Long id, @PathVariable boolean state) {
        Light light = lightService.getLightById(id);
        light.setStatus(state);
        lightService.updateLight(id, light);
        return light;
    }

    @DeleteMapping("/{id}")
    public void deleteLight(@PathVariable Long id) {
        lightService.deleteLight(id);
    }
}