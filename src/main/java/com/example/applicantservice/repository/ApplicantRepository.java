package com.example.applicantservice.repository;

import com.example.applicantservice.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	boolean existsByEmail(String email);
	
	void deleteByEmail(String email);
}
