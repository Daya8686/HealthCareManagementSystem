package com.hcms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcms.project.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{

	Patient findByPatientUsername(String username);

}
