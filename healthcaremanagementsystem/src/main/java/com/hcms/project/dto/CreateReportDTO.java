package com.hcms.project.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class CreateReportDTO {

	@NotBlank(message = "symtoms can not be empty!!")
	@Size(min = 2, message = "Symtoms must be atleast 2 charaters")
	private String symptoms;

	@PastOrPresent(message = "Date must be in present!!")
	@NotNull(message = "Date must not be blank")
	private LocalDate date;

	@Size(min = 2, max = 255, message = "Test Details must be at least 2 charaters.")
	@NotBlank(message = "This test details cannot left empty!!")
	private String testDetails;

	@NotBlank(message = "Appointment Patient Name must be filled!!")
	@Size(min = 3, max = 255, message = "Appointment Patient Name must be min 3 & max 255 charaters!")
	private String appointmentPatientName;

	@Min(value = 1, message = "Patient age field must be in range")
	@Max(value = 999, message = "Patient age field must be in range")
	private int appointmentPatientAge;

	@NotBlank(message = "Patient Gender can't be empty")
	@Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters")
	private String appointmentPatientGender;

	@NotNull
	private List<CreateMedicineDTO> createMedicineDTOs;

	public CreateReportDTO() {
		super();
	}

	public CreateReportDTO(
			@NotBlank(message = "symtoms can not be empty!!") @Size(min = 2, message = "Symtoms must be atleast 2 charaters") String symptoms,
			@PastOrPresent(message = "Date must be in present!!") @NotNull(message = "Date must not be blank") LocalDate date,
			@Size(min = 2, max = 255, message = "Test Details must be at least 2 charaters.") @NotBlank(message = "This test details cannot left empty!!") String testDetails,
			@NotBlank(message = "Appointment Patient Name must be filled!!") @Size(min = 3, max = 255, message = "Appointment Patient Name must be min 3 & max 255 charaters!") String appointmentPatientName,
			@Min(value = 1, message = "Patient age field must be in range") @Max(value = 999, message = "Patient age field must be in range") int appointmentPatientAge,
			@NotBlank(message = "Patient Gender can't be empty") @Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters") String appointmentPatientGender,
			@NotNull List<CreateMedicineDTO> createMedicineDTOs) {
		super();
		this.symptoms = symptoms;
		this.date = date;
		this.testDetails = testDetails;
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.createMedicineDTOs = createMedicineDTOs;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTestDetails() {
		return testDetails;
	}

	public void setTestDetails(String testDetails) {
		this.testDetails = testDetails;
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

	public List<CreateMedicineDTO> getCreateMedicineDTOs() {
		return createMedicineDTOs;
	}

	public void setCreateMedicineDTOs(List<CreateMedicineDTO> createMedicineDTOs) {
		this.createMedicineDTOs = createMedicineDTOs;
	}

	@Override
	public String toString() {
		return "CreateReportDTO [symptoms=" + symptoms + ", date=" + date + ", testDetails=" + testDetails
				+ ", appointmentPatientName=" + appointmentPatientName + ", appointmentPatientAge="
				+ appointmentPatientAge + ", appointmentPatientGender=" + appointmentPatientGender
				+ ", createMedicineDTOs=" + createMedicineDTOs + "]";
	}
	
	
	
}
