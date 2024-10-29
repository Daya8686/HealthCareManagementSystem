package com.hcms.project.dto;

import java.time.LocalDate;

import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateAppointmentDTO {

//	@NotNull(message = "Patient details must be filled!!")
//	private Patient patient;
	
	@NotBlank(message = "Appointment Patient Name must be filled!!")
	@Size(min = 3, max = 255, message = "Appointment Patient Name must be min 3 & max 255 charaters!")
	private String appointmentPatientName;
	
	@Min(value = 1, message = "Patient age field must be in range") 
	@Max(value = 999, message = "Patient age field must be in range")
	private int appointmentPatientAge;
	
	 @NotBlank(message = "Patient Gender can't be empty")
	 @Size(min=4, max=10, message = "Gender must be 4 to 10 letters")
	private String appointmentPatientGender;
	
	@NotNull(message = "Appointment Date must not be empty!")
	@FutureOrPresent(message = "Date must be present or future")
	private LocalDate appointmentDate;
	
	@NotBlank(message = "Chief Complaint can't be empty")
	@Size(min = 5, max= 255, message = "The Issue must be discribed with min 5 to 255 charateres")
	private String chiefComplaint;

	@NotBlank (message = "Symtoms can't be empty")
	@Size(min = 5, max= 255, message = "The symtoms must be discribed with min 5 to 255 charateres")
	private String symptoms;

	
	@NotNull(message = "Doctor details must be filled!!")
	private DoctorDTO doctorDTO;


	public CreateAppointmentDTO(
			@NotBlank(message = "Appointment Patient Name must be filled!!") @Size(min = 3, max = 255, message = "Appointment Patient Name must be min 3 & max 255 charaters!") String appointmentPatientName,
			@Min(value = 1, message = "Patient age field must be in range") @Max(value = 999, message = "Patient age field must be in range") int appointmentPatientAge,
			@NotBlank(message = "Patient Gender can't be empty") @Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters") String appointmentPatientGender,
			@NotNull(message = "Appointment Date must not be empty!") @FutureOrPresent(message = "Date must be present or future") LocalDate appointmentDate,
			@NotBlank(message = "Chief Complaint can't be empty") @Size(min = 5, max = 255, message = "The Issue must be discribed with min 5 to 255 charateres") String chiefComplaint,
			@NotBlank(message = "Symtoms can't be empty") @Size(min = 5, max = 255, message = "The symtoms must be discribed with min 5 to 255 charateres") String symptoms,
			@NotNull(message = "Doctor details must be filled!!") DoctorDTO doctorDTO) {
		super();
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.appointmentDate = appointmentDate;
		this.chiefComplaint = chiefComplaint;
		this.symptoms = symptoms;
		this.doctorDTO = doctorDTO;
	}


	public CreateAppointmentDTO() {
		super();
	}


	public String getAppointmentPatientName() {
		return appointmentPatientName;
	}


	public void setAppointmentPatientName(String appointmentPatientName) {
		this.appointmentPatientName = appointmentPatientName;
	}


	public int getAppointmentPatientAge() {
		return appointmentPatientAge;
	}


	public void setAppointmentPatientAge(int appointmentPatientAge) {
		this.appointmentPatientAge = appointmentPatientAge;
	}


	public String getAppointmentPatientGender() {
		return appointmentPatientGender;
	}


	public void setAppointmentPatientGender(String appointmentPatientGender) {
		this.appointmentPatientGender = appointmentPatientGender;
	}


	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public String getChiefComplaint() {
		return chiefComplaint;
	}


	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}


	public String getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}


	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}


	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}


	@Override
	public String toString() {
		return "AppointmentDTO [appointmentPatientName=" + appointmentPatientName + ", appointmentPatientAge="
				+ appointmentPatientAge + ", appointmentPatientGender=" + appointmentPatientGender
				+ ", appointmentDate=" + appointmentDate + ", chiefComplaint=" + chiefComplaint + ", symptoms="
				+ symptoms + ", doctorDTO=" + doctorDTO + "]";
	}


	
	
	

//	public Patient getPatient() {
//		return patient;
//	}
//
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}


	
}
	
