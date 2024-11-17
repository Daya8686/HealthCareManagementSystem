package com.hcms.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcms.project.dto.CreateAppointmentDTO;
import com.hcms.project.service.AppointmentServices;
import com.hcms.project.util.ApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AppointmentController {
	
	@Autowired
	private AppointmentServices appointmentServices;
	
	@GetMapping("/doctor/appointments")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getAllAppointments(){
		return appointmentServices.getAppointments();
	}
	
	@GetMapping("/doctor/myAppointments")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getAllMyAppointmentsOfDoctor(){
		return appointmentServices.getAllMyAppointmentsOfDoctor();
	}
	
	
	@PreAuthorize("hasAnyAuthority('PATIENT')")
	@PostMapping("/patient/bookappointment")
	public ResponseEntity<ApiResponseHandler> bookAppointment(@Valid @RequestBody CreateAppointmentDTO appointmentDTO){
		return appointmentServices.bookAppointment(appointmentDTO);
	}
	
	@GetMapping("/patient/appointments")
	@PreAuthorize("hasAnyAuthority('PATIENT')")
	public ResponseEntity<ApiResponseHandler> getAllPatientAppointments(){
		return appointmentServices.getAllAppointmentsOfPatient();
	}
	
	@GetMapping("/patient/appointments/{appointmentId}")
	@PreAuthorize("hasAnyAuthority('PATIENT','DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getAppointment(@PathVariable Long appointmentId){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		System.out.println(username);
//		Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//		System.out.println(role);
		return appointmentServices.getAppointmentById(appointmentId);
	}
	
	@PreAuthorize("hasAnyAuthority('PATIENT')")
	@PutMapping("/patient/appointments/{appointmentId}")
	public ResponseEntity<ApiResponseHandler> updateAppointmentDetails(@PathVariable Long appointmentId,@Valid @RequestBody CreateAppointmentDTO appointmentDTO){
		return appointmentServices.updateAppointment(appointmentId, appointmentDTO);
	}
	
	@DeleteMapping("/patient/appointments/{appointmentId}")
	@PreAuthorize("hasAnyAuthority('PATIENT')")
	public ResponseEntity<ApiResponseHandler> deleteAppointment(@PathVariable Long appointmentId ){
		return appointmentServices.deleteAppointment(appointmentId);
	}
	
	@GetMapping("/patient/appointment/search")
	@PreAuthorize("hasAnyAuthority('PATIENT','DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getAppointmentByUsername(@RequestParam(required = false) String username){
		System.out.println(username);
		if(!username.isBlank()) {
			return appointmentServices.searchAppointmentUserByUsername(username);
		}
		
		return appointmentServices.findAppointmentByAppointmentPatientUsername();
	}
	
	
	//----------------------------------------------------------------------------------------------------------
	
	
	
	

}
