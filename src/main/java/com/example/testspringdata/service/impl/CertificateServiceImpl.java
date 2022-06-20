package com.example.testspringdata.service.impl;

import com.example.testspringdata.dao.CertificateDAO;
import com.example.testspringdata.dtos.GiftCertificateDTO;
import com.example.testspringdata.mapper.Mapper;
import com.example.testspringdata.model.GiftCertificate;
import com.example.testspringdata.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDAO certificateDAO;
    private final Mapper mapper;

    @Autowired
    public CertificateServiceImpl(CertificateDAO certificateDAO, Mapper mapper) {
        this.certificateDAO = certificateDAO;
        this.mapper = mapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GiftCertificateDTO getById(Long id) {
        return mapper.mapToDTO(certificateDAO.getReferenceById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<GiftCertificateDTO> getAll() {
        return mapper.mapToListDTO(certificateDAO.findAll());
    }
}
