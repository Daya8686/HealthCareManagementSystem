package com.hcms.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcms.project.dto.CreateReportDTO;
import com.hcms.project.service.ReportService;
import com.hcms.project.util.ApiResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/MyReports")
	@PreAuthorize("hasAnyAuthority('DOCTOR','PATIENT')")
	public ResponseEntity<ApiResponseHandler> getMyReports(){
		
		return reportService.getAllReports();
		
	}
	
	@GetMapping("/MyReport/{reportId}")
	@PreAuthorize("hasAnyAuthority('DOCTOR','PATIENT')")
	public ResponseEntity<ApiResponseHandler> getMyReport(@PathVariable Long reportId){
		return reportService.getReportByReportId(reportId);
		
	}
	
	@GetMapping("/Hospital/AllReports")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getAllReportsOfHospital(){
		return reportService.getAllReportsOfHospital();
	}
	
	@GetMapping("/Hospital/AllReports/search")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> getPatientReportByAppointmentPatientName(@RequestParam(required = false) String appointmentPatientName){
		if(!appointmentPatientName.isBlank()) {
			return reportService.getReportByAppointmentPatientName(appointmentPatientName);
		}
		return reportService.getAllReportsOfHospital();
	}
	
	
	

	@GetMapping("/MyReports/search")
	@PreAuthorize("hasAnyAuthority('DOCTOR','PATIENT')")
	public ResponseEntity<ApiResponseHandler> getMyReportsByAppointmentPatientName(@RequestParam(required = false) String appointmentPatientName){
		if(!appointmentPatientName.isBlank()) {
			return reportService.getAllReportsBySearchAppointmentPatientName(appointmentPatientName);
		}
		return reportService.getAllReports();
		
	}
	
	@PostMapping("/MyReport/create")
	@PreAuthorize("hasAnyAuthority('DOCTOR')")
	public ResponseEntity<ApiResponseHandler> createReportOfPatient(@RequestParam Long appointmentId, @Valid @RequestBody CreateReportDTO createReportDTO){
		System.out.println(appointmentId+"-------"+createReportDTO);
		return reportService.createAReport(appointmentId,createReportDTO);
	}
	

}
