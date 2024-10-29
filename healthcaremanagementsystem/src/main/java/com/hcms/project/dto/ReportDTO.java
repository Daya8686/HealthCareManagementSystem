package com.hcms.project.dto;

import java.time.LocalDate;
import java.util.List;

public class ReportDTO {

	private Long reportId;

	private String symptoms;

	private LocalDate date;

	private String testDetails;

	private String appointmentPatientName;

	private int appointmentPatientAge;

	private String appointmentPatientGender;

	private List<MedicineDTO> medicinesDTO;

	public ReportDTO() {
		super();
	}

	public ReportDTO(Long reportId, String symptoms, LocalDate date, String testDetails, String appointmentPatientName,
			int appointmentPatientAge, String appointmentPatientGender, List<MedicineDTO> medicinesDTO) {
		super();
		this.reportId = reportId;
		this.symptoms = symptoms;
		this.date = date;
		this.testDetails = testDetails;
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.medicinesDTO = medicinesDTO;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
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

	public List<MedicineDTO> getMedicinesDTO() {
		return medicinesDTO;
	}

	public void setMedicinesDTO(List<MedicineDTO> medicinesDTO) {
		this.medicinesDTO = medicinesDTO;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportId=" + reportId + ", symptoms=" + symptoms + ", date=" + date + ", testDetails="
				+ testDetails + ", appointmentPatientName=" + appointmentPatientName + ", appointmentPatientAge="
				+ appointmentPatientAge + ", appointmentPatientGender=" + appointmentPatientGender + ", medicinesDTO="
				+ medicinesDTO + "]";
	}

}
