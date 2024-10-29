package com.hcms.project.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportId;

	@Column(nullable = false)
	private String symptoms;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private String testDetails;

	@Column(nullable = false)
	private String appointmentPatientName;

	@Column(nullable = false)
	private int appointmentPatientAge;

	@Column(nullable = false)
	private String appointmentPatientGender;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	// @NotNull
//    @JsonIgnore
	private Patient patient;

	@OneToOne(mappedBy = "report")
//    @JsonIgnore
	private Billing billing;

	@ManyToMany
	@JoinTable(name = "report_medicine", joinColumns = @JoinColumn(name = "report_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
//    @JsonIgnore
	private List<Medicine> medicines;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id", nullable = false)
	private Appointment appointment;

	public Report() {
		super();
	}

	public Report(Long reportId, String symptoms, LocalDate date, String testDetails, String appointmentPatientName,
			int appointmentPatientAge, String appointmentPatientGender, Patient patient, Billing billing,
			List<Medicine> medicines, Doctor doctor, Appointment appointment) {
		super();
		this.reportId = reportId;
		this.symptoms = symptoms;
		this.date = date;
		this.testDetails = testDetails;
		this.appointmentPatientName = appointmentPatientName;
		this.appointmentPatientAge = appointmentPatientAge;
		this.appointmentPatientGender = appointmentPatientGender;
		this.patient = patient;
		this.billing = billing;
		this.medicines = medicines;
		this.doctor = doctor;
		this.appointment = appointment;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", symptoms=" + symptoms + ", date=" + date + ", testDetails="
				+ testDetails + ", appointmentPatientName=" + appointmentPatientName + ", appointmentPatientAge="
				+ appointmentPatientAge + ", appointmentPatientGender=" + appointmentPatientGender + "]";
	}

}