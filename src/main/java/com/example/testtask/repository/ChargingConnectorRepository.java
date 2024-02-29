package com.example.testtask.repository;

import com.example.testtask.model.ChargingConnector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingConnectorRepository extends JpaRepository<ChargingConnector, Long> {

}
