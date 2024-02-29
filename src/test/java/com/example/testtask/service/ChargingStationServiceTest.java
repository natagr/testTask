package com.example.testtask.service;


import com.example.testtask.model.ChargingConnector;
import com.example.testtask.model.ChargingStation;
import com.example.testtask.model.base.ValidationResponse;
import com.example.testtask.model.constant.ConnectorType;
import com.example.testtask.model.dto.ChargingStationDto;
import com.example.testtask.model.mapper.ChargingStationMapper;
import com.example.testtask.repository.ChargingStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChargingStationServiceTest {
    @Mock
    private ChargingStationRepository chargingStationRepository;

    @Mock
    private ChargingStationMapper chargingStationMapper;

    @InjectMocks
    private ChargingStationService chargingStationService;

    private ChargingStationDto chargingStationDto;
    private ChargingConnector chargingConnector;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chargingStationDto = new ChargingStationDto();
        chargingConnector = new ChargingConnector();
        chargingConnector.setConnectorType(ConnectorType.CCS);
        chargingConnector.setMaximumPower(500);
        chargingStationDto.setChargingConnectors(Collections.singletonList(chargingConnector));
        chargingStationDto.setIsPublic(true);
        chargingStationDto.setTitle("Test Station");
        chargingStationDto.setDescription("Test Description");
        chargingStationDto.setAddress("Test Address");
        chargingStationDto.setGeoCoordinates("50.4501,30.5234");
    }

    @Test
    void validateChargingStation_WithValidData() {
        when(chargingStationMapper.toEntity(any(ChargingStationDto.class))).thenReturn(new ChargingStation());
        ValidationResponse response = chargingStationService.validateChargingStation(chargingStationDto);

        assertTrue(response.isValid());
        verify(chargingStationRepository, times(1)).save(any(ChargingStation.class));
    }

    @Test
    void validateChargingStation_WithInvalidConnectorCount() {
        chargingStationDto.setChargingConnectors(Collections.emptyList());
        ValidationResponse response = chargingStationService.validateChargingStation(chargingStationDto);

        assertFalse(response.isValid());
        assertEquals("The number of connectors must be between 1 and 8.", response.getMessages().get(0));
        verify(chargingStationRepository, never()).save(any(ChargingStation.class));
    }

    @Test
    void validateChargingStation_WithInvalidPublicStationDetails() {
        chargingStationDto.setTitle("");
        ValidationResponse response = chargingStationService.validateChargingStation(chargingStationDto);

        assertFalse(response.isValid());
        assertTrue(response.getMessages().contains("The title of the public station is required."));
        verify(chargingStationRepository, never()).save(any(ChargingStation.class));
    }
}
