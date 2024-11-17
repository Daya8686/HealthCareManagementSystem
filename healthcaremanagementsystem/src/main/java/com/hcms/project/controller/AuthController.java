package com.hcms.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcms.project.dto.CreateDoctorDTO;
import com.hcms.project.dto.PatientDTO;
import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;
import com.hcms.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/doctor")
    public Doctor registerDoctor(@Valid @RequestBody CreateDoctorDTO doctorDto) {
        return userService.registerDoctor(doctorDto);
    }

    @PostMapping("/register/patient")
    public Patient registerPatient(@Valid @RequestBody PatientDTO patientDto) {
        return userService.registerPatient(patientDto);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }
}