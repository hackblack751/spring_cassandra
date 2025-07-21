package org.huy.controller;

import lombok.RequiredArgsConstructor;
import org.huy.dto.sensor.SensorDataCreateRequest;
import org.huy.dto.sensor.SensorDataDto;
import org.huy.service.SensorDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/sensor/data")
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<?> saveSensorData(@RequestBody SensorDataCreateRequest request) {
        this.sensorDataService.saveSensorData(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<?> getSensorDataById(@PathVariable UUID sensorId,
                        @RequestParam(defaultValue = "0") int pageNum,
                        @RequestParam(defaultValue = "10") int pageSize,
                        @RequestParam(defaultValue = "created_at") String sort) {

        List<SensorDataDto> data = this.sensorDataService.getSensorData(sensorId, pageNum, pageSize);

        return ResponseEntity.ok(data);
    }
}
