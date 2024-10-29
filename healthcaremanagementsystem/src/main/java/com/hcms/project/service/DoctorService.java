package com.hcms.project.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hcms.project.dto.DoctorDTO;
import com.hcms.project.dto.UpdateDoctorDTO;
import com.hcms.project.entity.Address;
import com.hcms.project.entity.Doctor;
import com.hcms.project.exception.DoctorServiceExceptionHandler;
import com.hcms.project.repository.AddressRepo;
import com.hcms.project.repository.ContactRepo;
import com.hcms.project.repository.DoctorRepo;
import com.hcms.project.util.ApiResponseHandler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ContactRepo contactRepo;
	
	
	
	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllDoctors(){
		List<Doctor> allDoctors = doctorRepo.findAll();
		
		if(allDoctors.isEmpty()) {
			throw new DoctorServiceExceptionHandler("No Doctors are available", HttpStatus.NOT_FOUND);
		}
		List<DoctorDTO> doctorDtos = allDoctors.stream().map(doctor->modelMapper.map(doctor, DoctorDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(doctorDtos, HttpStatus.OK.value(), "Success"));
			
	}


	@Transactional
	public ResponseEntity<ApiResponseHandler> updateDoctorInfo(UpdateDoctorDTO updateDoctorDTO) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(username);
		byDoctorUsername.setDoctorName(updateDoctorDTO.getDoctorName());
		byDoctorUsername.setDoctorQualification(updateDoctorDTO.getDoctorQualification());
		byDoctorUsername.setDoctorUsername(updateDoctorDTO.getDoctorUsername());
		byDoctorUsername.setSpecialist(updateDoctorDTO.getSpecialist());
		byDoctorUsername.setDoctorGender(updateDoctorDTO.getDoctorGender());
		byDoctorUsername.setAddress(updateDoctorDTO.getAddress());
		Address savedAddress = addressRepo.save(updateDoctorDTO.getAddress());
		byDoctorUsername.setContact(updateDoctorDTO.getContact());
		contactRepo.save(updateDoctorDTO.getContact());
		
		Doctor savedDoctorDetails= doctorRepo.save(byDoctorUsername);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseHandler(updateDoctorDTO, HttpStatus.ACCEPTED.value(), "Updated!"));
	}
	
	@Transactional
	public ResponseEntity<ApiResponseHandler> getDoctorDetails(){
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(username);
		UpdateDoctorDTO doctorDetails = modelMapper.map(byDoctorUsername, UpdateDoctorDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(doctorDetails, HttpStatus.OK.value(), "Success!"));
	}


	@Transactional
	public ResponseEntity<ApiResponseHandler> deleteDoctorDetails() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 doctorRepo.deleteByDoctorUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler("Doctor profile is deleted!!", HttpStatus.OK.value(), "Deleted Account"));
	}
	
	
	
}