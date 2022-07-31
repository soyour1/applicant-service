package com.example.applicantservice.entity;

import com.example.applicantservice.entity.enumeration.CapacityStatus;
import com.example.applicantservice.entity.enumeration.EmploymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "t_projects")
public class Project {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "employment_mode", nullable = false)
	private EmploymentMode employmentMode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "capacity_status", nullable = false)
	private CapacityStatus capacityStatus;
	
	@Column(name = "duration_in_months")
	private String durationInMonths;
	
	@Column(name = "start_year", nullable = false)
	private String startYear;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "team_size", nullable = false)
	private Integer teamSize;
	
	@Column(name = "link_repo")
	private String linkRepo;
	
	@Column(name = "link_live")
	private String linkLive;
	
	@ManyToOne
	@JoinColumn(name = "application_id")
	private Applicant application;
}
