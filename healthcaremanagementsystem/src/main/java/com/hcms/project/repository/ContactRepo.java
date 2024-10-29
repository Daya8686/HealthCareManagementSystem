package com.hcms.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcms.project.entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{

}
