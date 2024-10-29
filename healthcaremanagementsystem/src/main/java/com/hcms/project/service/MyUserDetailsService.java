package com.hcms.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;
import com.hcms.project.repository.DoctorRepo;
import com.hcms.project.repository.PatientRepo;
import com.hcms.project.util.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Doctor doctor = doctorRepo.findByDoctorUsername(username);
        if (doctor != null) {
            return new UserPrincipal(doctor);
        }

        Patient patient = patientRepo.findByPatientUsername(username);
        if (patient != null) {
            return new UserPrincipal(patient);
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
