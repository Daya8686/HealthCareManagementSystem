package com.hcms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.hcms.project.entity.Appointment;
import com.hcms.project.entity.Doctor;
import com.hcms.project.entity.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	@Query("SELECT r FROM Appointment r WHERE r.appointmentPatientName LIKE %:appointmentPatientName%")
 	public Optional<List<Appointment>> findByAppointmentPatientName(@Param("appointmentPatientName") String appointmentPatientName);
	
	public Optional<List<Appointment>> findByDoctor(Doctor doctor);
	
	public Optional<List<Appointment>> findByPatient(Patient patient);

}
