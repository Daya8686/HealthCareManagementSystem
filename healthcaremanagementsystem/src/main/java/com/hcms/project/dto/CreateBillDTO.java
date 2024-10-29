package com.hcms.project.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class CreateBillDTO {

	@NotNull(message = "Consultation fee can not left empty.")
	@Size(min = 2)
	private Double consultationFee;

	@NotNull(message = "Medical fee cannot be left empty.")
	private Double medicalFee;

	@PastOrPresent(message = "must be filled date and time")
	private LocalDateTime billingDate;

	public CreateBillDTO() {
		super();
	}

	public CreateBillDTO(
			@NotNull(message = "Consultation fee can not left empty.") @Size(min = 2) Double consultationFee,
			@NotNull(message = "Medical fee cannot be left empty.") Double medicalFee,
			@PastOrPresent(message = "must be filled date and time") LocalDateTime billingDate) {
		super();
		this.consultationFee = consultationFee;
		this.medicalFee = medicalFee;
		this.billingDate = billingDate;
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

	@Override
	public String toString() {
		return "CreateBillDTO [consultationFee=" + consultationFee + ", medicalFee=" + medicalFee + ", billingDate="
				+ billingDate + "]";
	}
	
	

}
