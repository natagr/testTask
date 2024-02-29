package com.example.testtask.controller;

import com.example.testtask.model.dto.ChargingStationDto;
import com.example.testtask.model.base.ValidationResponse;
import com.example.testtask.service.ChargingStationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charging-stations")
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    @PostMapping("/validate")
    public ResponseEntity<ValidationResponse> validateChargingStation(@Valid @RequestBody ChargingStationDto station) {
        return ResponseEntity.ok(chargingStationService.validateChargingStation(station));
    }
}