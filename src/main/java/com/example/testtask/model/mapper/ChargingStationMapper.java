package com.example.testtask.model.mapper;

import com.example.testtask.model.ChargingStation;
import com.example.testtask.model.dto.ChargingStationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChargingStationMapper {
    ChargingStation toEntity(ChargingStationDto stationDTO);
}
