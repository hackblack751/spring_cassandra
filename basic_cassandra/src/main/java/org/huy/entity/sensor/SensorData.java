package org.huy.entity.sensor;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("sensor_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorData {

    /** using composite key: partition key + clustering key. */
    @PrimaryKey
    private SensorDataKey key;

    @Column("temperature")
    private Double temperature;

    private Double humidity;
}
