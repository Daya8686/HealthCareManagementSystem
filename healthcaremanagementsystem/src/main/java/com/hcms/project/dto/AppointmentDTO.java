package com.hcms.project.dto;

import java.time.LocalDate;

public class AppointmentDTO {

	private long appointmentId;

	private String appointmentPatientName;

	private int appointmentPatientAge;

	private String appointmentPatientGender;

	private LocalDate appointmentDate;

	private String chiefComplaint;

	private String symptoms;

	private DoctorDTO doctorDTO;

	private ReportDTO reportDTO;

	public AppointmentDTO(long appointmentId, String appointmentPatientName, int appointmentPatientAge,
			String appointmentPatientGender, LocalDate appointmentDate, String chiefComplaint, String symptoms,
			DoctorDTO doctorDTO, ReportDTO reportDTO) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.appointmentDate = appointmentDate;
		this.chiefComplaint = chiefComplaint;
		this.symptoms = symptoms;
		this.doctorDTO = doctorDTO;
		this.reportDTO = reportDTO;
	}

	public AppointmentDTO() {
		super();
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
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

	public ReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [appointmentId=" + appointmentId + ", appointmentPatientName=" + appointmentPatientName
				+ ", appointmentPatientAge=" + appointmentPatientAge + ", appointmentPatientGender="
				+ appointmentPatientGender + ", appointmentDate=" + appointmentDate + ", chiefComplaint="
				+ chiefComplaint + ", symptoms=" + symptoms + ", doctorDTO=" + doctorDTO + ", reportDTO=" + reportDTO
				+ "]";
	}
	
	
	
	
	

}
