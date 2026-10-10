package com.pup.taguig.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pup.taguig.dto.StudentResult;
import com.pup.taguig.service.StudentService;

// the container creates and holds this bean because of the @Service annotation
@Service
public class StudentServiceImpl implements StudentService {

	// a grade of 75 and above is the lowest passing remark
	private static final double PASSING_GRADE = 75.0;

	private static final double HIGHEST_GRADE = 100.0;

	@Override
	public StudentResult evaluateResult(String name, double grade) {

		StudentResult result = new StudentResult();
		result.setName(name);
		result.setGrade(grade);
		result.setRemarks(getRemarks(grade));
		result.setStatus(getStatus(grade));

		return result;
	}

	@Override
	public String getRemarks(double grade) {

		validateGrade(grade);

		// checked from the highest bracket downward
		if (grade >= 90.0) {
			return "Outstanding";
		} else if (grade >= 85.0) {
			return "Very Satisfactory";
		} else if (grade >= 80.0) {
			return "Satisfactory";
		} else if (grade >= PASSING_GRADE) {
			return "Fairly Satisfactory";
		}

		return "Did Not Meet Expectations";
	}

	@Override
	public String getStatus(double grade) {

		validateGrade(grade);

		return grade >= PASSING_GRADE ? "PASSED" : "FAILED";
	}

	@Override
	public double computeAverage(double grade1, double grade2, double grade3) {

		validateGrade(grade1);
		validateGrade(grade2);
		validateGrade(grade3);

		return roundToTwoDecimalPlaces((grade1 + grade2 + grade3) / 3);
	}

	private void validateGrade(double grade) {
		if (grade < 0.0 || grade > HIGHEST_GRADE) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Grade must be between 0 and 100 but received: " + grade);
		}
	}

	private double roundToTwoDecimalPlaces(double value) {
		return BigDecimal.valueOf(value)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}
}
