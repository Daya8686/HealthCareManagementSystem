package com.hcms.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;
import com.hcms.project.entity.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long>{
	
	public List<Report> findByPatient(Patient patient);
	
	public List<Report> findByDoctor(Doctor doctor);
	
	@Query("SELECT r FROM Report r WHERE r.appointmentPatientName LIKE %:appointmentPatientName%")
	public Optional <List<Report>> findByAppointmentPatientName(@Param("appointmentPatientName") String appointmentPatientName);


}
