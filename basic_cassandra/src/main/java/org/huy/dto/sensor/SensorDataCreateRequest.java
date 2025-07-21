package org.huy.dto.sensor;

import lombok.Data;

import java.util.UUID;

@Data
public class SensorDataCreateRequest {

    private UUID sensorId;
    private Double temperature;
    private Double humidity;
}
