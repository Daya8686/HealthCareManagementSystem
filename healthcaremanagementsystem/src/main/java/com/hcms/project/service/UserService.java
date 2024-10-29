package com.hcms.project.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcms.project.dto.AddressDTO;
import com.hcms.project.dto.ContactDTO;
import com.hcms.project.dto.CreateDoctorDTO;
import com.hcms.project.dto.PatientDTO;
import com.hcms.project.entity.Address;
import com.hcms.project.entity.Contact;
import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;
import com.hcms.project.repository.AddressRepo;
import com.hcms.project.repository.ContactRepo;
import com.hcms.project.repository.DoctorRepo;
import com.hcms.project.repository.PatientRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    @Autowired
    private AddressRepo addressRepo;
    
    @Autowired
    private ContactRepo contactRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public Doctor registerDoctor(CreateDoctorDTO doctorDto) {
    	Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
    	
    	AddressDTO address2 = doctorDto.getAddress();
    	Address address3 = modelMapper.map(address2, Address.class);
    	Address savedAddress = addressRepo.save(address3);
    	doctor.setAddress(savedAddress);
    	
    	ContactDTO contactDto = doctorDto.getContact();
    	Contact contact = modelMapper.map(contactDto, Contact.class);
    	Contact savedContact = contactRepo.save(contact);
    	doctor.setContact(savedContact);
    	
    	
    	
        doctor.setDoctorPassword(encoder.encode(doctor.getDoctorPassword()));
        return doctorRepo.save(doctor);
    }
    @Transactional
    public Patient registerPatient(PatientDTO patientDto) {
    	Patient patient = modelMapper.map(patientDto, Patient.class);
    	
    	AddressDTO addressDto=patientDto.getAddress();
    	Address address = modelMapper.map(addressDto, Address.class);
    	Address savedAddress = addressRepo.save(address);
    	patient.setAddress(savedAddress);
    	
    	
    	ContactDTO contactDto = patientDto.getContact();
    	Contact contact = modelMapper.map(contactDto, Contact.class);
    	Contact savedContact = contactRepo.save(contact);
    	patient.setContact(savedContact);
    	
        patient.setPatientPassword(encoder.encode(patientDto.getPatientPassword()));
        
        return patientRepo.save(patient);
    }

    public String login(String username, String password) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (auth.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token= jwtService.generateToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority());
//            String username2 = jwtService.extractUserName(token);
//            System.out.println(username2);
            return token;
        }
        throw new RuntimeException("Login failed");
    }
}