package com.employee.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	//Method to find employees by profession
	List<Employee> findByProfession(String profession);
	//Method to find employees by joining dates between
	List<Employee> findByDojBetween(Date doj, Date doj2);
	//Method to find employees by joining date
	List<Employee> findByDoj(Date doj);
}
