package com.hcms.project.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hcms.project.dto.CreateMedicineDTO;
import com.hcms.project.dto.CreateReportDTO;
import com.hcms.project.dto.MedicineDTO;
import com.hcms.project.dto.ReportDTO;
import com.hcms.project.entity.Appointment;
import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Medicine;
import com.hcms.project.entity.Patient;
import com.hcms.project.entity.Report;
import com.hcms.project.exception.AppointmentServiceExceptionHandler;
import com.hcms.project.exception.ReportServiceExceptionHandler;
import com.hcms.project.repository.AppointmentRepository;
import com.hcms.project.repository.DoctorRepo;
import com.hcms.project.repository.MedicineRepo;
import com.hcms.project.repository.PatientRepo;
import com.hcms.project.repository.ReportRepo;
import com.hcms.project.util.ApiResponseHandler;

import jakarta.transaction.Transactional;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepo reportRepo;
	
	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MedicineRepo medicineRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllReports() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean isDoctor = authorities.stream().anyMatch(role->role.getAuthority().equals("DOCTOR"));
		if(isDoctor) {
			System.out.println("I am Doctor");
			Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(username);
			List<Report> reportsOfDoctor = reportRepo.findByDoctor(byDoctorUsername);
			List<ReportDTO> collectioOfReportsByDoctor = reportsOfDoctor.stream().map(report->{
				ReportDTO reportDto = modelMapper.map(report, ReportDTO.class);
				List<Medicine> medicines = report.getMedicines();
				List<MedicineDTO> collectedMedicineDto = medicines.stream().map(medi-> modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
				reportDto.setMedicinesDTO(collectedMedicineDto);
				return reportDto;
				}).collect(Collectors.toList());
//			List<MedicineDTO> medicinesCollectedDto = reportsOfDoctor.stream().map(report->modelMapper.map(report.getMedicines(), MedicineDTO.class)).collect(Collectors.toList());
			
			if(collectioOfReportsByDoctor.isEmpty()) {
				throw new ReportServiceExceptionHandler("No Report found", HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectioOfReportsByDoctor, HttpStatus.OK.value(), "Success"));
		}
		boolean isPatient = authorities.stream().anyMatch(role->role.getAuthority().equals("PATIENT"));
		if(username!=null && isPatient) {
		System.out.println("I am patient");
		Patient byPatientUsername = patientRepo.findByPatientUsername(username);
		System.out.println(byPatientUsername);
		List<Report> reports = reportRepo.findByPatient(byPatientUsername);
		System.out.println(reports);
//		List<ReportDTO> collectioOfReportsOfPatient = reports.stream().map(report->modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
		List<ReportDTO> collectioOfReportsOfPatient = reports.stream().map(report->{
			ReportDTO reportDto = modelMapper.map(report, ReportDTO.class);
			List<Medicine> medicines = report.getMedicines();
			List<MedicineDTO> collectedMedicineDto = medicines.stream().map(medi-> modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
			reportDto.setMedicinesDTO(collectedMedicineDto);
			return reportDto;
			}).collect(Collectors.toList());
		if(collectioOfReportsOfPatient.isEmpty()) {
			throw new ReportServiceExceptionHandler("No Report found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectioOfReportsOfPatient, HttpStatus.OK.value(), "Success"));
		}
		throw new ReportServiceExceptionHandler("Username is null or Internal Process issue while retriving data!!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Transactional
	public ResponseEntity<ApiResponseHandler> getReportByReportId(Long reportId) {
		Optional<Report> reportById = reportRepo.findById(reportId);
		if(reportById.isEmpty()) {
			throw new ReportServiceExceptionHandler("No report available with id: "+reportId, HttpStatus.NOT_FOUND);
		}
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean isDoctor = authorities.stream().anyMatch(auth->auth.getAuthority().equals("DOCTOR"));
		
		if(isDoctor) {
			String doctorName = SecurityContextHolder.getContext().getAuthentication().getName();
			Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(doctorName);
			List<Report> byDoctor = reportRepo.findByDoctor(byDoctorUsername);
			Optional<Report> collectedReports = byDoctor.stream().filter(doc->doc.getReportId()==reportId).findFirst();
			if(collectedReports.isEmpty()) {
				 throw new ReportServiceExceptionHandler("No Report Found", HttpStatus.BAD_REQUEST);
			 }
		ReportDTO reportdto = modelMapper.map(collectedReports.get(), ReportDTO.class);
		 List<Medicine> medicines = reportById.get().getMedicines();
		 List<MedicineDTO> collectedMedicines = medicines.stream().map(medi->modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
		 reportdto.setMedicinesDTO(collectedMedicines);
		 

		 return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(reportdto, HttpStatus.OK.value(), "Success"));
	}
		
		
		String patientName = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient byPatientUsername = patientRepo.findByPatientUsername(patientName);
		List<Report> byPatientAllReports = reportRepo.findByPatient(byPatientUsername);
		Optional<Report> reportOfPatientById = byPatientAllReports.stream().filter(pat->pat.getReportId()==reportId).findFirst();
		if(reportOfPatientById.isEmpty()) {
			 throw new ReportServiceExceptionHandler("No Report Found", HttpStatus.BAD_REQUEST);
		 }
		ReportDTO reportdto = modelMapper.map(reportOfPatientById.get(), ReportDTO.class);
		 List<Medicine> medicines = reportById.get().getMedicines();
		 List<MedicineDTO> collectedMedicines = medicines.stream().map(medi->modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
		 reportdto.setMedicinesDTO(collectedMedicines);
		 return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(reportdto, HttpStatus.OK.value(), "Success"));
	}
	
	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllReportsOfHospital(){
		List<Report> allReports = reportRepo.findAll();
		List<ReportDTO> collectionOfReports = allReports.stream().map(report->{
			List<Medicine> medicines = report.getMedicines();
			List<MedicineDTO> listOfMedicines = medicines.stream().map(medi->modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
			ReportDTO reportDto = modelMapper.map(report, ReportDTO.class);
			reportDto.setMedicinesDTO(listOfMedicines);
			return reportDto;
			
			}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectionOfReports, HttpStatus.OK.value(), "Success!!"));
	}
	
	@Transactional
	public ResponseEntity<ApiResponseHandler> getReportByAppointmentPatientName(String appointmentPatientName){
		Optional<List<Report>> byAppointmentPatientName = reportRepo.findByAppointmentPatientName(appointmentPatientName);
		if(byAppointmentPatientName.isEmpty()) {
			throw new ReportServiceExceptionHandler("No Patient name found with provided appointment Patient name - "+appointmentPatientName, HttpStatus.NOT_FOUND);
		}
		List<ReportDTO> collectedReports = byAppointmentPatientName.get().stream().map(report->{
			List<Medicine> medicines = report.getMedicines();
			List<MedicineDTO> listOfMedicines = medicines.stream().map(medi->modelMapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
			ReportDTO reportDto = modelMapper.map(report, ReportDTO.class);
			reportDto.setMedicinesDTO(listOfMedicines);
			return reportDto;
			
			}).collect(Collectors.toList());
		if(collectedReports.isEmpty()) {
			throw new ReportServiceExceptionHandler("No Patient name found with provided appointment Patient name - "+appointmentPatientName, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectedReports, HttpStatus.OK.value(), "Success!!"));
	}
	
	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllReportsBySearchAppointmentPatientName(String appointmentPatientName) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean isDoctor = authorities.stream().anyMatch(role->role.getAuthority().equals("DOCTOR"));
		if(isDoctor) {
			System.out.println("I am Doctor");
			Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(username);
			List<Report> reportsOfDoctor = reportRepo.findByDoctor(byDoctorUsername);
			if(reportsOfDoctor.isEmpty()) {
				throw new ReportServiceExceptionHandler("No report found with patient name : "+appointmentPatientName, HttpStatus.NOT_FOUND);
			}
			List<ReportDTO> collectioOfReportsByDoctor = reportsOfDoctor.stream().map(report->modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
			List<Boolean> collectedSearchReport = collectioOfReportsByDoctor.stream().map(searchReport-> searchReport.getAppointmentPatientName().equalsIgnoreCase(appointmentPatientName)).collect(Collectors.toList());
			if(collectedSearchReport.isEmpty()) {
				throw new ReportServiceExceptionHandler("No report found with patient name : "+appointmentPatientName, HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectedSearchReport, HttpStatus.OK.value(), "Success"));
		}
		boolean isPatient = authorities.stream().anyMatch(role->role.getAuthority().equals("PATIENT"));
		if(username!=null && isPatient) {
		System.out.println("I am patient");
		Patient byPatientUsername = patientRepo.findByPatientUsername(username);
		System.out.println(byPatientUsername);
		List<Report> reports = reportRepo.findByPatient(byPatientUsername);
		if(reports.isEmpty()) {
			throw new ReportServiceExceptionHandler("No report found with patient name : "+appointmentPatientName, HttpStatus.NOT_FOUND);
		}
		System.out.println(reports);
		List<ReportDTO> collectioOfReportsOfPatient = reports.stream().map(report->modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
		List<ReportDTO> collectedSearchReport = collectioOfReportsOfPatient.stream().filter(searchReport-> searchReport.getAppointmentPatientName().equalsIgnoreCase(appointmentPatientName)).collect(Collectors.toList());
		
		if(collectedSearchReport.isEmpty()) {
			throw new ReportServiceExceptionHandler("No Report Found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseHandler(collectedSearchReport, HttpStatus.OK.value(), "Success"));
		}
		throw new ReportServiceExceptionHandler("Username is null or Internal Process issue while retriving data!!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Transactional
	public ResponseEntity<ApiResponseHandler> createAReport(Long appointmentId,CreateReportDTO createReportDTO) {
		
//		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
//		if(appointment.isEmpty()) {
//			throw new ReportServiceExceptionHandler("Appointment is invalid", HttpStatus.BAD_REQUEST);
//		}
		
		
						
		
//		modelMapper.typeMap(CreateReportDTO.class, Report.class).addMappings(mapper -> {
//	        mapper.map(CreateReportDTO::getAppointmentPatientName, Report::setAppointmentPatientName);
//	        mapper.map(CreateReportDTO::getAppointmentPatientAge, Report::setAppointmentPatientAge);
//	        mapper.map(CreateReportDTO::getAppointmentPatientGender, Report::setAppointmentPatientGender);
//	        mapper.skip(Report::setAppointment); // We manually set appointment later
//	        mapper.skip(Report::setPatient); // We manually set patient later
//	        mapper.skip(Report::setDoctor); // We manually set doctor later
//	    });
//		
//		
//		Report report = modelMapper.map(createReportDTO, Report.class);
		
		Report report= new Report();
		report.setAppointmentPatientName(createReportDTO.getAppointmentPatientName());
		report.setAppointmentPatientAge(createReportDTO.getAppointmentPatientAge());
		report.setAppointmentPatientGender(createReportDTO.getAppointmentPatientGender());
		report.setDate(createReportDTO.getDate());
		report.setSymptoms(createReportDTO.getSymptoms());
		report.setTestDetails(createReportDTO.getTestDetails());
		
		Appointment appointment = appointmentRepository.findById(appointmentId)
	            .orElseThrow(() -> new AppointmentServiceExceptionHandler("Appointment not found", HttpStatus.NOT_FOUND));
		report.setAppointment(appointment);
		
		Patient patient = appointment.getPatient();
		
		report.setPatient(patient);
		
		List<CreateMedicineDTO> createMedicineDTOs = createReportDTO.getCreateMedicineDTOs();
		List<Medicine> collectionOfMedicines = createMedicineDTOs.stream().map(medicineDto->modelMapper.map(medicineDto, Medicine.class)).collect(Collectors.toList());
		List<Medicine> saveAllMedicine = medicineRepo.saveAll(collectionOfMedicines);
		report.setMedicines(saveAllMedicine);
	
		
		String doctorName = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(doctorName);
		report.setDoctor(byDoctorUsername);

		
		Report savedReport = reportRepo.save(report);
		
		Appointment appointment1 = appointmentRepository.findById(appointmentId)
	            .orElseThrow(() -> new AppointmentServiceExceptionHandler("Appointment not found", HttpStatus.NOT_FOUND));
		appointment1.setReport(savedReport);
		appointmentRepository.save(appointment1);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseHandler(createReportDTO, HttpStatus.CREATED.value(), "Report Created"));
	}
	
//	public ResponseEntity<ApiResponseHandler> getAllReportsByHospital(){
//		List<Report> allReports = reportRepo.findAll();
//		allReports.stream().map(r->{
//			List<Medicine> medicines = r.getMedicines();
//			List<MedicineDTO> listOfMedicines = medicines.stream().map(m->modelMapper.map(m, MedicineDTO.class)).collect(Collectors.toList());
//			ReportDTO reportDTO = modelMapper.map(r, ReportDTO.class);
//			reportDTO.setMedicinesDTO(listOfMedicines);
//			return null;
//		}).collect(Collectors.toList());
//		}

}
