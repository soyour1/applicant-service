package com.example.applicantservice.service.dto.request;

import com.example.applicantservice.entity.enumeration.CapacityStatus;
import com.example.applicantservice.entity.enumeration.EmploymentMode;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProjectRequestDto {
	@NotBlank(message = "Project name is required")
	private String name;
	
	@NotNull(message = "Emplayment mode is required")
	private EmploymentMode employmentMode;
	
	@NotNull(message = "Capacity is required")
	private CapacityStatus capacityStatus;
	
	private String durationInMonths;
	
	@NotBlank(message = "Start year is required")
	private String startYear;
	
	@NotBlank(message = "Role is required")
	private String role;
	
	@NotNull(message = "Team size is required")
	private Integer teamSize;
	
	private String linkRepo;
	
	private String linkLive;
}
