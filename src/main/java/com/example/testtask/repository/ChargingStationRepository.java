package com.example.testtask.repository;

import com.example.testtask.model.ChargingConnector;
import com.example.testtask.model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {

}
