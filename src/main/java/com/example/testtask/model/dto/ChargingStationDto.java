package com.example.testtask.model.dto;

import com.example.testtask.model.ChargingConnector;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ChargingStationDto {

    @Size(max = 55, message = "Title must not exceed 55 characters.")
    private String title;

    @Size(max = 255, message = "Description must not exceed 255 characters.")
    private String description;

    private String address;

    @Pattern(regexp = "^-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?$", message = "Geo-coordinates must be in a valid format.")
    private String geoCoordinates;

    @NotNull(message = "Public status must be specified.")
    private Boolean isPublic;

    @NotNull(message = "Charging connectors list must not be null.")
    private List<ChargingConnector> chargingConnectors;
}
