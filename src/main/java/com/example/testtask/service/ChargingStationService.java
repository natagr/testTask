package com.example.testtask.service;

import com.example.testtask.model.ChargingConnector;
import com.example.testtask.model.base.ValidationResponse;
import com.example.testtask.model.dto.ChargingStationDto;
import com.example.testtask.model.mapper.ChargingStationMapper;
import com.example.testtask.repository.ChargingStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    private final ChargingStationMapper chargingStationMapper;

    @Transactional
    public ValidationResponse validateChargingStation(ChargingStationDto chargingStationDto) {
        ValidationResponse validationResponse = new ValidationResponse();

        validateConnectorsCount(chargingStationDto, validationResponse);
        validateConnectorDetails(chargingStationDto, validationResponse);
        if (Boolean.TRUE.equals(chargingStationDto.getIsPublic())) {
            validatePublicStationDetails(chargingStationDto, validationResponse);
        }
        if (validationResponse.getMessages().isEmpty()) {
            validationResponse.setValid(true);
            chargingStationRepository.save(chargingStationMapper.toEntity(chargingStationDto));
        } else {
            validationResponse.setValid(false);
        }
        return validationResponse;
    }

    private void validateConnectorsCount(ChargingStationDto chargingStationDto, ValidationResponse validationResponse) {
        int connectorsCount = chargingStationDto.getChargingConnectors().size();
        if (connectorsCount < 1 || connectorsCount > 8) {
            validationResponse.addMessage("The number of connectors must be between 1 and 8.");
        }
    }

    private void validateConnectorDetails(ChargingStationDto chargingStationDto, ValidationResponse validationResponse) {
        for (ChargingConnector chargingConnector : chargingStationDto.getChargingConnectors()) {
            if (chargingConnector.getConnectorType() == null || chargingConnector.getMaximumPower() == null) {
                validationResponse.addMessage("All connector parameters are required.");
                break;
            }
        }
    }

    private void validatePublicStationDetails(ChargingStationDto chargingStationDto, ValidationResponse validationResponse) {
        validateRequiredField(chargingStationDto.getTitle(), "The title of the public station is required.", validationResponse);
        validateRequiredField(chargingStationDto.getDescription(), "The description of the public station is required.", validationResponse);
        validateRequiredField(chargingStationDto.getAddress(), "The address of the public station is required.", validationResponse);
        validateRequiredField(chargingStationDto.getGeoCoordinates(), "Geo-coordinates of the public station are required.", validationResponse);
    }

    private void validateRequiredField(String fieldValue, String errorMessage, ValidationResponse validationResponse) {
        if (isNullOrEmpty(fieldValue)) {
            validationResponse.addMessage(errorMessage);
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}