package com.example.testspringdata.controller;

import com.example.testspringdata.authExceptions.InvalidPasswordException;
import com.example.testspringdata.dao.RegionDAO;
import com.example.testspringdata.dao.RegistrationDAO;
import com.example.testspringdata.dao.SensorDAO;
import com.example.testspringdata.dao.UserService;
import com.example.testspringdata.dto.*;
import com.example.testspringdata.mapper.UserMapper;
import com.example.testspringdata.message.AnswerMessage;
import com.example.testspringdata.model.unik.Region;
import com.example.testspringdata.model.unik.Registration;
import com.example.testspringdata.model.unik.Sensor;
import com.example.testspringdata.model.unik.User;
import com.example.testspringdata.security.jwtProvider.JwtProvider;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    private static final String ROLE = "ROLE_User";

    private final RegistrationDAO registrationDAO;
    private final SensorDAO sensorDAO;
    private final RegionDAO regionDAO;
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    @Autowired
    public Controller(RegistrationDAO registrationDAO, SensorDAO sensorDAO, RegionDAO regionDAO, UserService userService, UserMapper userMapper, JwtProvider jwtProvider) {
        this.registrationDAO = registrationDAO;
        this.sensorDAO = sensorDAO;
        this.regionDAO = regionDAO;
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtProvider = jwtProvider;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/registrations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Registration> findRegistrations() {
        return registrationDAO.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/registrations/sort/{time}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Registration> getRegsByTimeSort(@PathVariable(name = "time") String time) {
        return registrationDAO.getAllByTimeSortByTempAsc(time);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/registrations/{beginTime}/{endTime}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MaxMinEntity findMaxAndMinTemperatureFromGap(@PathVariable String beginTime, @PathVariable String endTime) {
        return new MaxMinEntity(registrationDAO.findMaxTemperatureFromGap(beginTime, endTime),
                registrationDAO.findMinTemperatureFromGap(beginTime, endTime));
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/registrations/deviation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Double> getDeviation() {
        return registrationDAO.getDeviation();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/regions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Region> findRegions() {
        return regionDAO.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Sensor> findSensors() {
        return sensorDAO.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/registrations/{regionNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Registration> findRegistrationsByRegionNumber(@PathVariable Integer regionNumber) {
        List<Sensor> sensors = sensorDAO.findAllByRegionNumber(regionNumber);
        List<Registration> registrations = new ArrayList<>();
        List<Registration> regBuffer;
        for (Sensor sensor : sensors) {
            regBuffer = registrationDAO.findAllBySensorNumber((int) sensor.getId());
            registrations.addAll(regBuffer);
            regBuffer.clear();
        }
        return registrations;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnswerMessage register(@RequestBody AuthUserDTO authUserDTO) throws ServiceException {
        authUserDTO.setPassword(BCrypt.hashpw(authUserDTO.getPassword(), BCrypt.gensalt()));
        userService.save(new User(authUserDTO.getEmail(), authUserDTO.getPassword(), ROLE));
        return new AnswerMessage("User has been created.", HttpStatus.ACCEPTED.toString());
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/auth/sighIn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse sighIn(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidPasswordException {
        RoleUserDTO roleUserDTO = userMapper.mapFromUser(userService.findFirstUserByEmail(authenticationRequest.getLogin()));
        String token;
        if (BCrypt.checkpw(authenticationRequest.getPassword(), roleUserDTO.getPassword())) {
            token = jwtProvider.generateToken(authenticationRequest.getLogin());
        } else {
            throw new InvalidPasswordException("Wrong password");
        }
        return new AuthenticationResponse(roleUserDTO.getId(), token, roleUserDTO.getRole());
    }


}
