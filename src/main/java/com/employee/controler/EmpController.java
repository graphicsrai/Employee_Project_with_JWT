package com.employee.controler;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Service.EmpService;
import com.employee.dto.EmployeeDto;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	//Welcome api for testing 
	@RequestMapping("/")
	public String welcome()
	{
		return "Welcome to emp portal";
	}
	
	
	//1 Save Employee Api
	@PostMapping("/save")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto empDto)
	{
		EmployeeDto employeeDto=this.empService.createEmployee(empDto);
		if(employeeDto!=null)
		{
			return new ResponseEntity<EmployeeDto>(employeeDto,HttpStatus.CREATED);
		}
		return null;
	}
	
	//2 Update Employee Api
	@PutMapping("/update/{empId}")
	public ResponseEntity<EmployeeDto> updateEmp(@PathVariable Integer empId,@RequestBody EmployeeDto empDto)
	{
		EmployeeDto updatedEmpDto=this.empService.updateEmployee(empId, empDto);
		if(updatedEmpDto!=null)
		{
			return new ResponseEntity<EmployeeDto>(updatedEmpDto,HttpStatus.OK);
		}
		return null;
	}
	
	//3 Get all employees Api
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAllEmp()
	{
		List<EmployeeDto> employeeDtos=this.empService.findAllEmployees();
		if(employeeDtos!=null)
		{
			return new ResponseEntity<List<EmployeeDto>>(employeeDtos,HttpStatus.OK);
		}
		return null;
	}
	
	//4 Find Employee by id 
	@GetMapping("/getOne/{empId}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable Integer empId)
	{
		EmployeeDto empDto=this.empService.findEmpById(empId);
		if(empDto!=null)
		{
			return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.OK);
		}
		return null;
	}
	
	//5 get employee by profession 
	@GetMapping("byProfession/{profession}")
	public ResponseEntity<List<EmployeeDto>> getByProfession(@PathVariable String profession)
	{
		List<EmployeeDto> empDtos=this.empService.getEmpByProfession(profession);
		if(empDtos!=null)
		{
			return new ResponseEntity<List<EmployeeDto>>(empDtos,HttpStatus.OK);
		}
		return null;
	}
	// find emolpyee by date
	@GetMapping("/byDoj")
	public ResponseEntity<List<EmployeeDto>> findByDOJ(@RequestParam(value="doj") Date doj)
	{
		List<EmployeeDto> empDtos=this.empService.getEmpByDate(doj);
		if(empDtos!=null)
		{
			return new ResponseEntity<List<EmployeeDto>>(empDtos,HttpStatus.OK);
		}
		return null;
	}
	
	//6 get employee by date
	@GetMapping("byDate/{date1}/{date2}")
	public ResponseEntity<List<EmployeeDto>> findByDate(@PathVariable Date date1,@PathVariable Date date2)
	{
		List<EmployeeDto> empDtos=this.empService.getEmpByDate(date1, date2);
		if(empDtos!=null)
		{
			return new ResponseEntity<List<EmployeeDto>>(empDtos,HttpStatus.OK);
		}
		return null;
	}
	
	//7 Delete employee
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<String> deleteEmp(@PathVariable Integer empId)
	{
		String empDto=this.empService.deleteEmp(empId);
		if(empDto!=null)
		{
			return new ResponseEntity<String>(empDto,HttpStatus.OK);
		}
		return null;
	}
}
