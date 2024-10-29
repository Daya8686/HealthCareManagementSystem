package com.hcms.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcms.project.entity.Appointment;
import com.hcms.project.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

	public Doctor findByDoctorUsername(String username);
	
	public void deleteByDoctorUsername(String username);
	
	public Optional<List<Doctor>> findByAppointments(Appointment appointments);

}
