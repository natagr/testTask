package com.example.testtask.model.dto;

import com.example.testtask.model.constant.ConnectorType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargingConnectorDto {

    @Enumerated(EnumType.STRING)
    @NotNull
    private ConnectorType connectorType;

    @NotNull
    @Min(1)
    @Max(350000)
    private Long maximumPower;
}
