package com.spring.employee.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.spring.employee.model.Employee;
import com.spring.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

@Autowired	
 private EmployeeService service;
	
	@RequestMapping(value = "/employeeinfo" , method=RequestMethod.GET)
	public List<Employee> getEmployeeInfo() {
	
		List<Employee> li = service.getEmployeeData();
		
		return li;
	}
	
	@RequestMapping(value= "/postEmployeeData" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee employeePostCal(@RequestBody Employee employee) {
		
	//validation
		if(Objects.isNull(employee.getId()) || (employee.getId()==0)) {
			throw new IllegalArgumentException("Employee id is mandatory field or invalid argument passed");
		}
		
		//service
		
		Employee employeeObj = service.getEmployeeDataBasedOnId(employee.getId());
		
		return employeeObj;
	}
	
	@RequestMapping(value= "/putEmployeeData" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
			public String putemployeeData(@RequestBody Employee ep) {
		boolean status = service.addemployeeToDb(ep);
		if(status==true) {
			return "Employee details are added";
		}
		else {
			return "Employee details are not added";
		}
	}
	
	@RequestMapping(value= "/delEmployeeData/{Id}" , method = RequestMethod.DELETE)
	public String delemployeeData(@PathVariable int Id) {
boolean status = service.delemployeeFromDb(Id);
if(status==true) {
	return "Deleted Successfully";
}
else {
	return "Failed to delete";
}
}
}
