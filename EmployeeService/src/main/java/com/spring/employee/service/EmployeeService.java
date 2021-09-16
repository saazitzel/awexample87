package com.spring.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.dao.EmployeeDao;
import com.spring.employee.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	public List<Employee> getEmployeeData() {
	List<Employee> epList =dao.getEmployeeData();
	return epList;
	}
	
	public Employee getEmployeeDataBasedOnId(int Id) {

		
		List<Employee> epList = dao.getEmployeeDataBasedOnId(Id);
		return epList.get(0);
		
}
	
	public boolean addemployeeToDb(Employee ep) {
		boolean status = dao.addemployeeToDb(ep);
		return status;
	}
	
	public boolean delemployeeFromDb(int Id) {
		boolean status = dao.delemployeeFromDb(Id);
		return status;
}
}

