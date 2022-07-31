package com.example.applicantservice.repository;

import com.example.applicantservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	void deleteByApplicationId(Long id);
}
