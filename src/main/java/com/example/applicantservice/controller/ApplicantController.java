package com.example.applicantservice.controller;

import com.example.applicantservice.entity.Message;
import com.example.applicantservice.export.ApplicationPDFExporter;
import com.example.applicantservice.service.ApplicantService;
import com.example.applicantservice.service.dto.request.ApplicantRequestDto;
import com.example.applicantservice.service.dto.response.ApplicantResponseDto;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/application")
@RestController
@RequiredArgsConstructor
public class ApplicantController {

	private final ApplicantService applicantService;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	private final HttpServletRequest htttpRequest;

	@GetMapping
	public ResponseEntity<List<ApplicantResponseDto>> getApplication(){
		return ResponseEntity.ok(applicantService.getApplication());
	}

	@PostMapping()
	public ResponseEntity<ApplicantResponseDto> addApplication(@Valid @RequestBody ApplicantRequestDto request) {
		return ResponseEntity.ok(applicantService.addApplication(request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteApplication(@PathVariable Long id){
		applicantService.deleteApplication(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateApplication(@PathVariable Long id, @Valid @RequestBody ApplicantRequestDto request){
		applicantService.updateApplication(id, request);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/export")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, ImagingOpException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerkey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headerValue);
		
		List<ApplicantResponseDto> applicationList = applicantService.getApplication();
		
		ApplicationPDFExporter exporter = new ApplicationPDFExporter(applicationList);
		exporter.export(response);
		
		Message message = new Message("user", htttpRequest.getRemoteAddr(), "export pdf");
		kafkaTemplate.send("log", message.toString());
	}

}
