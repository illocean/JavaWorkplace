package com.pup.taguig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pup.taguig.dto.StudentResult;
import com.pup.taguig.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/result")
	public StudentResult getStudentResult(@RequestParam String name, @RequestParam double grade) {

		return studentService.evaluateResult(name, grade);
	}

	@GetMapping("/remarks")
	public String getRemarks(@RequestParam double grade) {

		return studentService.getRemarks(grade);
	}

	@GetMapping("/status")
	public String getStatus(@RequestParam double grade) {

		return studentService.getStatus(grade);
	}

	@GetMapping("/average")
	public String getAverage(@RequestParam double grade1,
							 @RequestParam double grade2,
							 @RequestParam double grade3) {

		double average = studentService.computeAverage(grade1, grade2, grade3);

		return "Average Grade: " + average;
	}
}
