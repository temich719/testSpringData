package com.example.testspringdata.dao;

import com.example.testspringdata.model.unik.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDAO extends JpaRepository<Region, Long> {
}
