package com.opensource.iotdemo.service;

import com.opensource.iotdemo.entity.Light;
import com.opensource.iotdemo.dao.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LightService {

    @Autowired
    private LightRepository lightRepository;

    @Autowired
    private MqttService mqttService;

    public List<Light> getAllLights() {
        return lightRepository.findAll();
    }

    public Light getLightById(Long id) {
        return lightRepository.findById(id).orElse(null);
    }

    public Light createLight(Light lock) {
        Light savedLight = lightRepository.save(lock);
        mqttService.publish("light/created", 1, "Light created: " + savedLight.toMessage());
        return savedLight;
    }

    public Light updateLight(Long id, Light lightDetails) {
        Light light = lightRepository.findById(id).orElse(null);
        if (light != null) {
            light.setId(lightDetails.getId());
            light.setLightName(null != lightDetails.getLightName() ? lightDetails.getLightName() : light.getLightName());
            light.setStatus(lightDetails.getStatus());
            Light updatedLight = lightRepository.save(light);
            mqttService.publish("light/updated", 1, "Light updated: " + updatedLight.toMessage());
            return updatedLight;
        }
        return null;
    }

    public void deleteLight(Long id) {
        lightRepository.deleteById(id);
        mqttService.publish("light/deleted", 1, "Light deleted with ID: " + id);
    }
}
