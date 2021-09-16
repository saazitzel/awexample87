package com.spring.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.spring.employee.model.Employee;

@Component
public class EmployeeDao {
	
	@Autowired
	private JdbcTemplate template;
	
	public List<Employee> getEmployeeData() {
String sql = "select * from empdetails";

List<Employee> employeeList = template.query(sql, new ResultSetExtractor<List<Employee>>() {
	
	@Override
	public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Employee> list = new ArrayList<>();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setId(rs.getInt("emp_id"));
			employee.setName(rs.getString("emp_name"));
			employee.setAddress(rs.getString("emp_address"));
			employee.setNumber(rs.getInt("emp_number"));
			employee.setProject(rs.getString("emp_project"));
			employee.setManager(rs.getString("emp_manager"));
			list.add(employee);
		}
		return list;
		}
	});
	
	employeeList.stream().forEach(employee->{
		System.out.println(employee.getId() + "-" + employee.getName());
});

return employeeList;
}

	 public List<Employee> getEmployeeDataBasedOnId(int Id) {
			
			String sql = "select * from empdetails where emp_id = "+Id;
			
			List<Employee> employeeList = template.query(sql, new ResultSetExtractor<List<Employee>>() {
				
				@Override
				public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<Employee> list = new ArrayList<>();
					while(rs.next()) {
						Employee employee = new Employee();
						employee.setId(rs.getInt("emp_id"));
						employee.setName(rs.getString("emp_name"));
						employee.setAddress(rs.getString("emp_address"));
						employee.setNumber(rs.getInt("emp_number"));
						employee.setProject(rs.getString("emp_project"));
						employee.setManager(rs.getString("emp_manager"));
						list.add(employee);
					}
					return list;
					}
				});
				
				employeeList.stream().forEach(employee->{
					System.out.println(employee.getId() + "-" + employee.getName());
			});
			
		return employeeList;
	}

	 
	 public boolean addemployeeToDb(Employee ep) {
			 boolean status = false;
	  	   try {
	  		   String sql = "INSERT INTO empdetails (emp_id,emp_name,emp_address,emp_number,emp_project,emp_manager) VALUES ("+ep.getId()+",'"+ep.getName()+"','"+ep.getAddress()+"','"+ep.getNumber()+"','"+ep.getProject()+"','"+ep.getManager()+"')";
	  		   
	  		   template.execute(sql);
	  		   status=true;
	  	   }
	  	   catch(Exception e) {
	  		   status=false;
	  	   }
	  	   return status;
			
		}

	public boolean delemployeeFromDb(int Id) {
		boolean status = false;
		   try {
			   String sql = "delete from empdetails where emp_id="+Id;
			   
			   template.execute(sql);
			   status=true;
		   }
		   catch(Exception e) {
			   status=false;
		   }
		   return status;
		
	}
}
		

