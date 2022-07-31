package com.example.applicantservice.service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApplicantResponseDto {
	private Long id;
	
	private String email;
	
	private String name;
	
	private String gitUser;
	
	private List<ProjectResponseDto> projects;
}
