package org.huy.repository;

import org.huy.entity.sensor.SensorData;
import org.huy.entity.sensor.SensorDataKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorDataRepository extends CassandraRepository<SensorData, SensorDataKey> {

    @Query("""
            SELECT *
            FROM sensor_data
            WHERE sensor_id = :sensorId
            """)
    List<SensorData> findByIdWithPageable(UUID sensorId, Pageable pageable);
}
