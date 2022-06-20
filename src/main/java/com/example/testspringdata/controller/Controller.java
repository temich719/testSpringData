package com.example.testspringdata.controller;

import com.example.testspringdata.dtos.GiftCertificateDTO;
import com.example.testspringdata.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/testSpringData")
public class Controller {

    private final CertificateService certificateService;

    @Autowired
    public Controller(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GiftCertificateDTO> getAll(){
        return certificateService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GiftCertificateDTO getCertificateById(@PathVariable Long id){
        return certificateService.getById(id);
    }

    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String becomeUser(@AuthenticationPrincipal OAuth2User principal){
        System.out.println(principal);
        return principal.getAttribute("login");
    }

}
