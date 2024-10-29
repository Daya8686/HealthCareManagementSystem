package com.hcms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcms.project.entity.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {

}
