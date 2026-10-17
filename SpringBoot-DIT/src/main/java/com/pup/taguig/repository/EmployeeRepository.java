package com.pup.taguig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pup.taguig.model.Employee;
import com.pup.taguig.model.Student;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee save(Employee employee);

}
