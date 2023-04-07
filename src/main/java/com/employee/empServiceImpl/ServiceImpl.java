package com.employee.empServiceImpl;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Entity.Employee;
import com.employee.Service.EmpService;
import com.employee.dto.EmployeeDto;
import com.employee.repo.EmployeeRepo;
@Service
public class ServiceImpl implements EmpService {
	
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	public ModelMapper modelMapper;
	
	//1 Create employee  method
	@Override
	public EmployeeDto createEmployee(EmployeeDto emp) {
		Employee employee=this.modelMapper.map(emp, Employee.class);
		Employee savedEmp=this.empRepo.save(employee);
		return this.modelMapper.map(savedEmp, EmployeeDto.class);
	}
	
	//2 update employee  method
	@Override
	public EmployeeDto updateEmployee(Integer empId, EmployeeDto empDto) {
		Employee emp=this.empRepo.findById(empId).orElse(null);
		if(emp!=null)
		{
			emp.setName(empDto.getName());
			emp.setDoj(empDto.getDoj());
			emp.setSalary(empDto.getSalary());
			emp.setProfession(empDto.getProfession());
			
			Employee updatedEmp=this.empRepo.save(emp);
			return this.modelMapper.map(updatedEmp, EmployeeDto.class);
		}
		return null;
	}
	
	//3 Find all employees
	@Override
	public List<EmployeeDto> findAllEmployees() {
		List<Employee> empList=this.empRepo.findAll();
		List<EmployeeDto> EmpDtoList=empList.stream().map((emp)-> this.modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
		return EmpDtoList;
	}
	
	//4 Find employee by id
	@Override
	public EmployeeDto findEmpById(Integer empId) {
		Employee emp=this.empRepo.findById(empId).orElse(null);
		if(emp!=null)
		{
			return this.modelMapper.map(emp, EmployeeDto.class);
		}
		return null;
	}
	//5 get employee by profession 
	@Override
	public List<EmployeeDto> getEmpByProfession(String profession) {
		List<Employee> emp=this.empRepo.findByProfession(profession);
		List<EmployeeDto> empDtos=emp.stream().map((e)-> this.modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
		return empDtos;
	}
	//find by date 
	@Override
	public List<EmployeeDto> getEmpByDate(Date d1) {
		List<Employee> emp=this.empRepo.findByDoj(d1);
		if(emp!=null)
		{
			List<EmployeeDto> empDtos=emp.stream().map((e)-> this.modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
			return empDtos;
		}
		return null;
	}

	//6 get employee by date
	@Override
	public List<EmployeeDto> getEmpByDate(Date d1, Date d2) {
		List<Employee> emp=this.empRepo.findByDojBetween(d1, d2);
		List<EmployeeDto> empDtos=emp.stream().map((e)-> this.modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
		return empDtos;
	}
	//7 Delete employee
	@Override
	public String deleteEmp(Integer empId) {
		Employee emp=this.empRepo.findById(empId).orElse(null);
		if(emp!=null)
		{
			this.empRepo.deleteById(empId);
			return "Employee has been deleted";
		}
		return null;
	}

	
}
