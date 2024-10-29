package com.hcms.project.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity

public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
   
	private Patient patient;
	
	@Column(nullable = false)
	private String appointmentPatientName;
	@Column(nullable = false)
	private int appointmentPatientAge;
	
	@Column(nullable = false)
	private String appointmentPatientGender;
	
	@Column(nullable = false)
	private LocalDate appointmentDate;
	
	@Column(nullable = false)
	private String chiefComplaint;

	@Column(nullable = false)
	private String symptoms;

	@ManyToOne
    @JoinColumn(name = "doctor_id")
	
	private Doctor doctor;
	
	@OneToOne
	@JoinColumn(name="report_id")
	private Report report;
	

	public Appointment() {
		super();
	}


	public Appointment(long appointmentId, Patient patient, String appointmentPatientName, int appointmentPatientAge,
			String appointmentPatientGender, LocalDate appointmentDate, String chiefComplaint, String symptoms,
			Doctor doctor, Report report) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.appointmentDate = appointmentDate;
		this.chiefComplaint = chiefComplaint;
		this.symptoms = symptoms;
		this.doctor = doctor;
		this.report = report;
	}


	public long getAppointmentId() {
		return appointmentId;
	}


	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
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


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Report getReport() {
		return report;
	}


	public void setReport(Report report) {
		this.report = report;
	}


	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient + ", appointmentPatientName="
				+ appointmentPatientName + ", appointmentPatientAge=" + appointmentPatientAge
				+ ", appointmentPatientGender=" + appointmentPatientGender + ", appointmentDate=" + appointmentDate
				+ ", chiefComplaint=" + chiefComplaint + ", symptoms=" + symptoms + ", doctor=" + doctor + ", report="
				+ report + "]";
	}



}
