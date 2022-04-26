package com.suwi.pdf.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.suwi.pdf.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suwi.pdf.model.Employee;
import com.suwi.pdf.util.PDFGenerator;

@RestController
@RequestMapping("/api/pdf")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();

		ByteArrayInputStream bis = PDFGenerator.employeePDFReport(employees);
		//ByteArrayInputStream bis = PDFGenerator.test();


		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employees.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}