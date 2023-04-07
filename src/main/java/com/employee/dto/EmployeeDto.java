package com.employee.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
	
	private int id;
	private String name;
	private Date doj;
	private String salary;
	private String profession;
}
