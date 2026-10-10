package com.pup.taguig.service;

import com.pup.taguig.dto.StudentResult;

/*
 * abstraction: whatever is declared here must be implemented in StudentServiceImpl
 *
 * interface = abstraction
 */
public interface StudentService {

	// builds the full result of a student: name, grade, remarks and status
	public StudentResult evaluateResult(String name, double grade);

	// returns the remarks only, based on the grading guide
	public String getRemarks(double grade);

	// returns PASSED or FAILED, based on the passing grade
	public String getStatus(double grade);

	// returns the average of three grades, rounded to two decimal places
	public double computeAverage(double grade1, double grade2, double grade3);
}
