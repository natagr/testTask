package com.example.testtask.model;

import com.example.testtask.model.base.AbstractIdentifiable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "charging_stations")
public class ChargingStation extends AbstractIdentifiable {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "geo_coordinates")
    private String geoCoordinates;

    @Column(name = "is_public")
    private Boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "charging_station_id")
    private List<ChargingConnector> chargingConnectors;
}
