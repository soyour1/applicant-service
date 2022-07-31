package com.example.applicantservice.service.mapper;

import com.example.applicantservice.entity.Applicant;
import com.example.applicantservice.service.dto.request.ApplicantRequestDto;
import com.example.applicantservice.service.dto.response.ApplicantResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ApplicantMappper {
	
	private final ProjectMapper projectMapper;
	
	@Transactional
	public Applicant convertToApplication(ApplicantRequestDto request) {
		Applicant applicant = new Applicant();
		applicant.setEmail(request.getEmail());
		applicant.setName(request.getName());
		applicant.setGitUser(request.getGitUser());
		request.getProjects().stream().forEach(project -> applicant.addProjects(projectMapper.convertToProject(project)));
		return applicant;
	}
	
	@Transactional
	public ApplicantResponseDto convertToResponseDto(Applicant application) {
		return ApplicantResponseDto
				  .builder()
				  .id(application.getId())
				  .name(application.getName())
				  .email(application.getEmail())
				  .gitUser(application.getGitUser())
				  .projects(application.getProjects().stream().map(projectMapper::convertToResponseDto).collect(Collectors.toList()))
				  .build();
	}
	
}
