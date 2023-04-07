package com.employee.Service;

import java.sql.Date;
import java.util.List;

import com.employee.dto.EmployeeDto;

public interface EmpService {
	//Create employee abstract method
	EmployeeDto createEmployee(EmployeeDto emp);
	//update employee abstract method
	EmployeeDto updateEmployee( Integer empId,EmployeeDto emp);
	//Find all employees
	List<EmployeeDto> findAllEmployees();
	//Find employee by id
	EmployeeDto findEmpById(Integer empId);
	//get employee by profession 
	List<EmployeeDto> getEmpByProfession(String profession);
	//get employee by date
	List<EmployeeDto> getEmpByDate(Date d1);
	//get employee by date between
	List<EmployeeDto> getEmpByDate(Date d1,Date d2);
	//Delete employee
	String deleteEmp(Integer empId);
}
