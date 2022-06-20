package com.example.testspringdata.service;

import com.example.testspringdata.dtos.GiftCertificateDTO;

import java.util.List;

public interface CertificateService {

    GiftCertificateDTO getById(Long id);

    List<GiftCertificateDTO> getAll();

}
