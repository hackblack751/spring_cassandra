package org.huy.dto.sensor;

import lombok.Data;
import org.huy.entity.sensor.SensorData;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SensorDataDto {

    private UUID sensorId;

    private LocalDateTime createdAt;

    private Double temperature;

    private Double humidity;

    public SensorDataDto() {}

    public SensorDataDto(SensorData entity) {
        this.sensorId = entity.getKey().getSensorId();
        this.createdAt = entity.getKey().getCreatedAt();
        this.temperature = entity.getTemperature();
        this.humidity = entity.getHumidity();
    }
}
