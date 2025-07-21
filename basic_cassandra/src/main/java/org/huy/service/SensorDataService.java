package org.huy.service;

import lombok.RequiredArgsConstructor;
import org.huy.dto.sensor.SensorDataCreateRequest;
import org.huy.dto.sensor.SensorDataDto;
import org.huy.entity.sensor.SensorData;
import org.huy.entity.sensor.SensorDataKey;
import org.huy.repository.SensorDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    private final ModelMapper modelMapper;

    public void saveSensorData(SensorDataCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        SensorData data = SensorData.builder()
                .key(new SensorDataKey(request.getSensorId(), now))
                .temperature(request.getTemperature())
                .humidity(request.getHumidity())
                .build();

        this.sensorDataRepository.save(data);
    }

    public List<SensorDataDto> getSensorData(UUID sensorId, int pageNum, int pageSize) {
//        PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "updated_at"));
        List<SensorData> dataList = this.sensorDataRepository.findByIdWithPageable(sensorId, PageRequest.of(pageNum, pageSize));

        return dataList.stream().map(SensorDataDto::new).toList();
    }
}
