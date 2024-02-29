package com.example.testtask.model;

import com.example.testtask.model.base.AbstractIdentifiable;
import com.example.testtask.model.constant.ConnectorType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "charging_connectors")
public class ChargingConnector extends AbstractIdentifiable {

    @Enumerated(EnumType.STRING)
    @Column(name = "connector_type")
    private ConnectorType connectorType;

    @Column(name = "maximum_power")
    private Integer maximumPower;
}
