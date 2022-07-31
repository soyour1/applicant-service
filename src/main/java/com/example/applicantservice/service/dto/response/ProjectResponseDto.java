package com.example.applicantservice.service.dto.response;

import com.example.applicantservice.entity.enumeration.CapacityStatus;
import com.example.applicantservice.entity.enumeration.EmploymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {
	
	private Long id;
	
	private String name;
	
	private EmploymentMode employmentMode;
	
	private CapacityStatus capacityStatus;
	
	private String durationInMonths;
	
	private String startYear;
	
	private String role;
	
	private Integer teamSize;
	
	private String linkRepo;
	
	private String linkLive;
}
