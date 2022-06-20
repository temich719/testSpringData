package com.example.testspringdata.dao;

import com.example.testspringdata.model.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateDAO extends JpaRepository<GiftCertificate, Long> {

}
