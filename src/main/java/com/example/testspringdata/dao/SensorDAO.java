package com.example.testspringdata.dao;

import com.example.testspringdata.model.unik.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDAO extends JpaRepository<Sensor, Long> {

    List<Sensor> findAllByRegionNumber(Integer regionNumber);

}
