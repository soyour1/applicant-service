package com.example.applicantservice.service;

import com.example.applicantservice.service.dto.request.ApplicantRequestDto;
import com.example.applicantservice.service.dto.response.ApplicantResponseDto;

import java.util.List;

public interface ApplicantService {
	ApplicantResponseDto addApplication(ApplicantRequestDto request);
	
	List<ApplicantResponseDto> getApplication();
	
	void updateApplication(Long id, ApplicantRequestDto request);
	
	void deleteApplication(Long id);
}
