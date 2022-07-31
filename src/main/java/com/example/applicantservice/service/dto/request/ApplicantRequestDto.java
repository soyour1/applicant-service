package com.example.applicantservice.service.dto.request;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.Valid;


import java.util.List;
@Data
@Builder
public class ApplicantRequestDto {
	@NotBlank(message = "Email address is required")
	private String email;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Git user is required")
	private String gitUser;
	
	@Valid
	@NotEmpty
	private List<ProjectRequestDto> projects;
}
