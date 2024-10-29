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

import com.hcms.project.dto.AppointmentDTO;
import com.hcms.project.dto.CreateAppointmentDTO;
import com.hcms.project.dto.DoctorDTO;
import com.hcms.project.dto.MedicineDTO;
import com.hcms.project.dto.ReportDTO;
import com.hcms.project.entity.Appointment;
import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Medicine;
import com.hcms.project.entity.Patient;
import com.hcms.project.entity.Report;
import com.hcms.project.exception.AppointmentServiceExceptionHandler;
import com.hcms.project.repository.AppointmentRepository;
import com.hcms.project.repository.DoctorRepo;
import com.hcms.project.repository.PatientRepo;
import com.hcms.project.util.ApiResponseHandler;

import jakarta.transaction.Transactional;

@Service
public class AppointmentServices {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Transactional
	public ResponseEntity<ApiResponseHandler> getAppointments() {

		List<Appointment> allAppointments = appointmentRepository.findAll();
		if (allAppointments == null || allAppointments.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No Appointments are present", HttpStatus.NOT_FOUND);
		}
		List<AppointmentDTO> collectAllAppointment = allAppointments.stream().map(app -> {

			AppointmentDTO appointmentsDto = mapper.map(app, AppointmentDTO.class);
			Doctor doctor = app.getDoctor();

			DoctorDTO doctorDto = mapper.map(doctor, DoctorDTO.class);
			Report report = app.getReport();
			appointmentsDto.setDoctorDTO(doctorDto);

			if (app.getReport() != null) {
				List<Medicine> medicines = app.getReport().getMedicines();
				List<MedicineDTO> medicinesDto = medicines.stream().map(medi -> mapper.map(medi, MedicineDTO.class))
						.collect(Collectors.toList());
				ReportDTO reportDto = mapper.map(report, ReportDTO.class);
				reportDto.setMedicinesDTO(medicinesDto);
				appointmentsDto.setReportDTO(reportDto);
			}

			return appointmentsDto;
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(collectAllAppointment, HttpStatus.OK.value(), "Success"));

	}

	/* Booking for an appointment */
	@Transactional
	public ResponseEntity<ApiResponseHandler> bookAppointment(CreateAppointmentDTO appointmentDTO) {
		Appointment appointment = mapper.map(appointmentDTO, Appointment.class);

		Optional<Doctor> doctor = doctorRepo.findById(appointmentDTO.getDoctorDTO().getDoctorId());
		if (doctor.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("Selected Doctor is invalid", HttpStatus.BAD_REQUEST);
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Patient> patientDetails = Optional.of(patientRepo.findByPatientUsername(username));
		if (patientDetails.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No Patient found ", HttpStatus.BAD_REQUEST);
		}
		appointment.setPatient(patientDetails.get());
		appointment.setDoctor(doctor.get());
		Appointment savedAppointment = appointmentRepository.save(appointment);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponseHandler(appointmentDTO, HttpStatus.CREATED.value(), "Appointment Booked!!"));
	}

	/* Updating Appointment of Patient */
	@Transactional
	public ResponseEntity<ApiResponseHandler> updateAppointment(Long appointmentId,
			CreateAppointmentDTO appointmentDTO) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
		if (appointment.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("Appointment with provided ID is Invalid!!",
					HttpStatus.NOT_FOUND);
		}
		Appointment appointmentToSave = mapper.map(appointmentDTO, Appointment.class);
		appointmentToSave.setAppointmentId(appointmentId);
		System.out.println(appointmentToSave);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Patient> patientDetails = Optional.of(patientRepo.findByPatientUsername(username));
		appointmentToSave.setPatient(patientDetails.get());
		Appointment savedAppointment = appointmentRepository.save(appointmentToSave);
		if (savedAppointment == null) {
			throw new AppointmentServiceExceptionHandler("Internal server issue, Unable to save updated appointment!!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(appointmentDTO, HttpStatus.OK.value(), "Appointment Updated !!"));
	}

	/* Get a appointment of patient by appointment id */
	@Transactional
	public ResponseEntity<ApiResponseHandler> getAppointmentById(Long appointmentId) {
		Collection<? extends GrantedAuthority> roleOfUser = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// Check if the user is a doctor
		boolean isDoctor = roles.stream().anyMatch(role -> role.getAuthority().equals("DOCTOR"));

		if (isDoctor) {

			Optional<Appointment> appointmentById = appointmentRepository.findById(appointmentId);
			if (appointmentById.isEmpty()) {

				throw new AppointmentServiceExceptionHandler("Appointment ID is invalid!", HttpStatus.BAD_REQUEST);
			}

			AppointmentDTO appointmentDTO = mapper.map(appointmentById, AppointmentDTO.class);

			Doctor doctor = appointmentById.get().getDoctor();
			DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);

			Report report = appointmentById.get().getReport();
			if (report != null) {
				List<Medicine> medicines = report.getMedicines();
				List<MedicineDTO> medicinesDtoList = medicines.stream().map(medi -> mapper.map(medi, MedicineDTO.class))
						.collect(Collectors.toList());
				ReportDTO reportDTO = mapper.map(report, ReportDTO.class);
				reportDTO.setMedicinesDTO(medicinesDtoList);
				appointmentDTO.setReportDTO(reportDTO);
			}

			appointmentDTO.setDoctorDTO(doctorDTO);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseHandler(appointmentDTO, HttpStatus.OK.value(), "Success"));
		}

		Patient patient = patientRepo.findByPatientUsername(username);

		if (patient == null) {
			throw new AppointmentServiceExceptionHandler("Patient not found", HttpStatus.NOT_FOUND);
		}

		boolean hasAppointment = patient.getAppointments().stream()
				.anyMatch(appointment -> appointment.getAppointmentId() == (appointmentId));

		if (!hasAppointment) {

			throw new AppointmentServiceExceptionHandler("You are not allowed to access this appointment",
					HttpStatus.FORBIDDEN);
		}

		// Fetch the appointment by ID since it belongs to the patient
		Optional<Appointment> appointmentById = appointmentRepository.findById(appointmentId);
		if (appointmentById.isEmpty()) {

			throw new AppointmentServiceExceptionHandler("Appointment ID is invalid!", HttpStatus.BAD_REQUEST);
		}

		AppointmentDTO appointmentDTO = mapper.map(appointmentById, AppointmentDTO.class);

		Doctor doctor = appointmentById.get().getDoctor();
		DoctorDTO doctorDTO = mapper.map(doctor, DoctorDTO.class);
		appointmentDTO.setDoctorDTO(doctorDTO);

		if (appointmentDTO.getReportDTO() != null) {
			Report report = appointmentById.get().getReport();
			List<Medicine> medicines = report.getMedicines();
			List<MedicineDTO> listOfMedicines = medicines.stream().map(medi -> mapper.map(medi, MedicineDTO.class))
					.collect(Collectors.toList());
			ReportDTO reportDTO = mapper.map(report, ReportDTO.class);
			reportDTO.setMedicinesDTO(listOfMedicines);
			appointmentDTO.setReportDTO(reportDTO);
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(appointmentDTO, HttpStatus.OK.value(), "Success"));
	}

	/* All Appointments of Patient */
	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllAppointmentsOfPatient() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Patient> patientDetails = Optional.of(patientRepo.findByPatientUsername(username));
		if (patientDetails.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No Patient found ", HttpStatus.BAD_REQUEST);
		}
		List<Appointment> allAppointments = patientDetails.get().getAppointments();
		if (allAppointments.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No Appointment is booked", HttpStatus.NOT_FOUND);
		}
//		allAppointments
//		Changing to dto
		List<AppointmentDTO> appointmentDTO = allAppointments.stream().map(app -> {
			AppointmentDTO appointmentDto = mapper.map(app, AppointmentDTO.class);

			DoctorDTO doctorDto = mapper.map(app.getDoctor(), DoctorDTO.class);

			appointmentDto.setDoctorDTO(doctorDto);

			if (app.getReport() != null) {
				Report report = app.getReport();
				List<Medicine> medicines = report.getMedicines();
				List<MedicineDTO> listOfMedicineDTOs = medicines.stream().map(medi->mapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
				ReportDTO reportDto = mapper.map(report, ReportDTO.class);
				reportDto.setMedicinesDTO(listOfMedicineDTOs);
				appointmentDto.setReportDTO(reportDto);
			}
			return appointmentDto;
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(appointmentDTO, HttpStatus.OK.value(), "Success"));
	}

	/* Delete a appointment */
	@Transactional
	public ResponseEntity<ApiResponseHandler> deleteAppointment(Long appointmentId) {
		appointmentRepository.deleteById(appointmentId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(null, HttpStatus.OK.value(), "Deleted Appointment!!"));
	}

	@Transactional
	public ResponseEntity<ApiResponseHandler> findAppointmentByAppointmentPatientUsername() {

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		Optional<Boolean> isDoctor = authorities.stream().map(aut -> aut.equals("DOCTOR")).findFirst();
		if (isDoctor.get()) {
			String doctorName = SecurityContextHolder.getContext().getAuthentication().getName();
			Doctor byDoctorUsername = doctorRepo.findByDoctorUsername(doctorName);
			Optional<List<Appointment>> appointmentsOfCurrentDoctor = appointmentRepository
					.findByDoctor(byDoctorUsername);
			List<AppointmentDTO> collectionOfAppointmentsOfDoctor = appointmentsOfCurrentDoctor.get().stream()
					.map(appointment -> mapper.map(appointment, AppointmentDTO.class)).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseHandler(collectionOfAppointmentsOfDoctor, HttpStatus.OK.value(), "Success"));
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Patient byPatientUsername = patientRepo.findByPatientUsername(username);
		Optional<List<Appointment>> byPatient = appointmentRepository.findByPatient(byPatientUsername);
		if (byPatient.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No Appointment found with username" + username,
					HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ApiResponseHandler(byPatient.get(), HttpStatus.OK.value(), "Found user for username" + username));
	}

	@Transactional
	public ResponseEntity<ApiResponseHandler> searchAppointmentUserByUsername(String username) {

		Optional<List<Appointment>> byAppointmentPatientName = appointmentRepository
				.findByAppointmentPatientName(username);
		if (byAppointmentPatientName.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No appointment found!!", HttpStatus.NO_CONTENT);
		}

		// Step 2: Check if the current user is a doctor
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		boolean isDoctor = authorities.stream().anyMatch(role -> role.getAuthority().equals("DOCTOR"));

		if (isDoctor) {
			// Step 3: Get logged-in doctor's username
			String doctorUsername = SecurityContextHolder.getContext().getAuthentication().getName();

			// Step 4: Find the doctor by username
			Doctor doctor = doctorRepo.findByDoctorUsername(doctorUsername);
			if (doctor == null) {
				throw new AppointmentServiceExceptionHandler("Doctor not found!!", HttpStatus.NOT_FOUND);
			}

			// Step 5: Filter the appointments that belong to this doctor
			List<Appointment> filteredAppointments = byAppointmentPatientName.get().stream()
					.filter(appointment -> appointment.getDoctor().getDoctorId().equals(doctor.getDoctorId()))
					.collect(Collectors.toList());

			if (filteredAppointments.isEmpty()) {
				throw new AppointmentServiceExceptionHandler("No appointments found for this doctor!!",
						HttpStatus.NO_CONTENT);
			}

			List<AppointmentDTO> resultOfAppointmentDto = filteredAppointments.stream().map(app -> {
				AppointmentDTO appointmentDTO = mapper.map(app, AppointmentDTO.class);
				Doctor doctor2 = app.getDoctor();
				DoctorDTO doctorDto = mapper.map(doctor2, DoctorDTO.class);
				appointmentDTO.setDoctorDTO(doctorDto);
				if (app.getReport() != null) {
					Report report = app.getReport();
					List<Medicine> medicines = report.getMedicines();
					List<MedicineDTO> listOfMedicines = medicines.stream()
							.map(medi -> mapper.map(medi, MedicineDTO.class)).collect(Collectors.toList());
					ReportDTO reportDto = mapper.map(report, ReportDTO.class);
					reportDto.setMedicinesDTO(listOfMedicines);
					appointmentDTO.setReportDTO(reportDto);
				}
				return appointmentDTO;

			}).collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseHandler(resultOfAppointmentDto, HttpStatus.OK.value(), "Success"));

		}

		String usernameToCheck = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Patient> patientDetails = Optional.of(patientRepo.findByPatientUsername(usernameToCheck));

		Optional<Boolean> result = Optional.of(patientDetails.get().getAppointments().stream()
				.map(app -> app.getAppointmentPatientName().equalsIgnoreCase(username)).findFirst().orElse(false));

		if (result.get() == false) {

			throw new AppointmentServiceExceptionHandler("You are not allowed to check", HttpStatus.FORBIDDEN);

		}

		List<Appointment> collectedAppointment = patientDetails.get().getAppointments().stream()
				.filter(app -> app.getAppointmentPatientName().equalsIgnoreCase(username)).collect(Collectors.toList());
		List<AppointmentDTO> appointmentDTO = collectedAppointment.stream().map(app -> {
			AppointmentDTO appointmentDto = mapper.map(app, AppointmentDTO.class);
			DoctorDTO doctorDto = mapper.map(app.getDoctor(), DoctorDTO.class);
			appointmentDto.setDoctorDTO(doctorDto);

			if (app.getReport() != null) {
				Report report = app.getReport();
				List<Medicine> medicines = report.getMedicines();
				List<MedicineDTO> listOfMedicines = medicines.stream().map(medi -> mapper.map(medi, MedicineDTO.class))
						.collect(Collectors.toList());
				ReportDTO reportDto = mapper.map(report, ReportDTO.class);
				reportDto.setMedicinesDTO(listOfMedicines);
				appointmentDto.setReportDTO(reportDto);

			}
			return appointmentDto;
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(appointmentDTO, HttpStatus.OK.value(), "Success"));
	}

	@Transactional
	public ResponseEntity<ApiResponseHandler> getAllMyAppointmentsOfDoctor() {
		String doctorName = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor byDoctor = doctorRepo.findByDoctorUsername(doctorName);
		Optional<List<Appointment>> allAppointments = appointmentRepository.findByDoctor(byDoctor);
		if (allAppointments.isEmpty()) {
			throw new AppointmentServiceExceptionHandler("No appointment booked by patient", HttpStatus.NOT_FOUND);
		}

		List<AppointmentDTO> collectAllAppointment = allAppointments.get().stream().map(app -> {

			AppointmentDTO appointmentsDto = mapper.map(app, AppointmentDTO.class);
			Doctor doctor = app.getDoctor();

			DoctorDTO doctorDto = mapper.map(doctor, DoctorDTO.class);
			Report report = app.getReport();
			appointmentsDto.setDoctorDTO(doctorDto);

			if (app.getReport() != null) {
				List<Medicine> medicines = app.getReport().getMedicines();
				List<MedicineDTO> medicinesDto = medicines.stream().map(medi -> mapper.map(medi, MedicineDTO.class))
						.collect(Collectors.toList());
				ReportDTO reportDto = mapper.map(report, ReportDTO.class);
				reportDto.setMedicinesDTO(medicinesDto);
				appointmentsDto.setReportDTO(reportDto);
			}

			return appointmentsDto;
		}).collect(Collectors.toList());

//		List<AppointmentDTO> collectionOfAppointments = allAppointements.get().stream().map(app-> mapper.map(app, AppointmentDTO.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponseHandler(collectAllAppointment, HttpStatus.OK.value(), "Success"));
	}

}
