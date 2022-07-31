package com.example.applicantservice.service.impl;

import com.example.applicantservice.entity.Applicant;
import com.example.applicantservice.entity.Message;
import com.example.applicantservice.repository.ApplicantRepository;
import com.example.applicantservice.service.ApplicantService;
import com.example.applicantservice.service.dto.request.ApplicantRequestDto;
import com.example.applicantservice.service.dto.response.ApplicantResponseDto;
import com.example.applicantservice.service.mapper.ApplicantMappper;
import com.example.applicantservice.service.mapper.ProjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ApplicantServiceImpl implements ApplicantService {
	
	private  final ApplicantRepository applicantRepository;


//	private final Logg loggerService;
	
	private  final ApplicantMappper applicantMappper;
	
	private final ProjectMapper projectMapper;
	
	private final HttpServletRequest htttpRequest;
	private final KafkaTemplate<String, String> kafkaTemplate;
	@Override
	public ApplicantResponseDto addApplication(ApplicantRequestDto request) {
		Message message = new Message(request.getEmail(), htttpRequest.getRemoteAddr(), "Add application");
		kafkaTemplate.send("log", message.toString());
		return saveApplication(request);
	}
	
	public ApplicantResponseDto saveApplication(ApplicantRequestDto request){
		Applicant application = applicantMappper.convertToApplication(request);
//		request.getProjects().stream().forEach(project -> application.addProjects(projectMapper.convertToProject(project)));
//		loggerService.sendLogService(request.getEmail(), "deleted application");
		
		return applicantMappper.convertToResponseDto(applicantRepository.save(application));
	}
	
	@Override
	public List<ApplicantResponseDto> getApplication() {
		return applicantRepository.findAll().stream().map(applicantMappper::convertToResponseDto).collect(Collectors.toList());
		
	}
	
	@Override
	public void updateApplication(Long id, ApplicantRequestDto request) {
		Applicant application = applicantRepository.findById(id).orElseThrow(() -> new RuntimeException("Application is not found"));
		if(applicantRepository.existsByEmail(request.getEmail())){
//			applicationRepository.deleteByEmail(request.getEmail());
			applicantRepository.delete(application);
			saveApplication(request);
			return;
		}
		application.setEmail(request.getEmail());
		application.setName(request.getName());
		application.setGitUser(request.getGitUser());
		
		Message message = new Message(request.getEmail(), htttpRequest.getRemoteAddr(), "Update application");
		kafkaTemplate.send("log", message.toString());
	}
	
	@Override
	@Transactional
	public void deleteApplication(Long id) {
		applicantRepository.deleteById(id);
	}
}
