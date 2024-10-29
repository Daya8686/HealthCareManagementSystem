//package com.hcms.project.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.hcms.project.entity.Patient;
//import com.hcms.project.repository.PatientRepo;
//
//@Service
//public class PatientService {
//	
//	@Autowired
//    private JWTService jwtService;
//
//    @Autowired
//   private AuthenticationManager authManager;
//
//    @Autowired
//    private PatientRepo repo;
//
//
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//    public Patient register(Patient patient) {
//    	patient.setPatientPassword(encoder.encode(patient.getPatientPassword()));
//        repo.save(patient);
//        return patient;
//    }
//
//    public String verify(Patient patient) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(patient.getPatientUsername(), patient.getPatientPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(patient.getPatientUsername());
//        } else {
//            return "fail";
//        }
//    }
//
//}
