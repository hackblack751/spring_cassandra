package org.huy.entity.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@PrimaryKeyClass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataKey implements Serializable {

    @PrimaryKeyColumn(name = "sensor_id", type = PrimaryKeyType.PARTITIONED)
    private UUID sensorId;

    @PrimaryKeyColumn(name = "created_at", type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime createdAt;
}
