package com.hcms.project.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "patientbill")
public class Billing {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    @Column(nullable = false)
    private Double consultationFee;
    
    @Column(nullable = false)
    private Double medicalFee;
    
    @Column(nullable = false)
    private LocalDateTime billingDate;

//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//  //  @NotNull
//    private Patient patient;
    
    @OneToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
  //  @NotNull
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "report_id")
  //  @NotNull
    private Report report;
    
    

	public Billing() {
		super();
	}



	public Billing(Long billingId, Double consultationFee, Double medicalFee, LocalDateTime billingDate,
			Appointment appointment, Doctor doctor, Report report) {
		super();
		this.billingId = billingId;
		this.consultationFee = consultationFee;
		this.medicalFee = medicalFee;
		this.billingDate = billingDate;
		this.appointment = appointment;
		this.doctor = doctor;
		this.report = report;
	}



	public Long getBillingId() {
		return billingId;
	}



	public void setBillingId(Long billingId) {
		this.billingId = billingId;
	}



	public Double getConsultationFee() {
		return consultationFee;
	}



	public void setConsultationFee(Double consultationFee) {
		this.consultationFee = consultationFee;
	}



	public Double getMedicalFee() {
		return medicalFee;
	}



	public void setMedicalFee(Double medicalFee) {
		this.medicalFee = medicalFee;
	}



	public LocalDateTime getBillingDate() {
		return billingDate;
	}



	public void setBillingDate(LocalDateTime billingDate) {
		this.billingDate = billingDate;
	}



	public Appointment getAppointment() {
		return appointment;
	}



	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
		return "Billing [billingId=" + billingId + ", consultationFee=" + consultationFee + ", medicalFee=" + medicalFee
				+ ", billingDate=" + billingDate + ", appointment=" + appointment + ", doctor=" + doctor + ", report="
				+ report + "]";
	}



	
	
    
}