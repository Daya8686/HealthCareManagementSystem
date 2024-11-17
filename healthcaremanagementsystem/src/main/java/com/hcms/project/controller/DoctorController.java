package com.hcms.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcms.project.dto.UpdateDoctorDTO;
import com.hcms.project.service.DoctorService;
import com.hcms.project.util.ApiResponseHandler;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/Doctors")
	@PreAuthorize("hasAnyAuthority('DOCTOR','PATIENT')")
	public ResponseEntity<ApiResponseHandler> getAllDoctors(){
		System.out.println("---------------");
		return doctorService.getAllDoctors();
	}
	
	@PutMapping("/Doctor")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> updateDoctorInfo( @RequestBody UpdateDoctorDTO updateDoctorDTO){
		System.out.println(updateDoctorDTO);
		return doctorService.updateDoctorInfo(updateDoctorDTO);
	}
	
	@GetMapping("/Doctor")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getDoctorDetails(){
		return doctorService.getDoctorDetails();
	}
	
	@DeleteMapping("/Doctor/accountDelete")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> deleteDoctor(){
		return doctorService.deleteDoctorDetails();
	}
	
}
