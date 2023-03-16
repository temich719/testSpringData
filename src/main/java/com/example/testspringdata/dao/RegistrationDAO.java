package com.example.testspringdata.dao;

import com.example.testspringdata.model.unik.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationDAO extends JpaRepository<Registration, Long> {

    @Query(value = "SELECT MIN(temperature) from registrations where DATE(registrationTime) >= DATE(:beginTime) and " +
            "DATE(registrationTime) <= DATE(:endTime);", nativeQuery = true)
    Double findMinTemperatureFromGap(@Param("beginTime") String beginTime,
                                      @Param("endTime") String endTime);

    @Query(value = "SELECT MAX(temperature) from registrations where DATE(registrationTime) >= DATE(:beginTime) and " +
            "DATE(registrationTime) <= DATE(:endTime);", nativeQuery = true)
    Double findMaxTemperatureFromGap(@Param("beginTime") String beginTime,
                                      @Param("endTime") String endTime);

    @Query(value = "SELECT Temperature - (SELECT AVG(Temperature) from registrations) from registrations", nativeQuery = true)
    List<Double> getDeviation();

    @Query(value = "SELECT * FROM registrations WHERE registrationTime = :time ORDER BY temperature ASC;", nativeQuery = true)
    List<Registration> getAllByTimeSortByTempAsc(@Param("time") String time);

    List<Registration> findAllBySensorNumber(Integer sensorNumber);
}
