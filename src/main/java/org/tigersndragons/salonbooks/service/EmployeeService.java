package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Employee;

@Service
public interface EmployeeService {

	public  Employee getDefaultEmployee() ;
	
	public Employee getEmployee(String uname, String pwrd);
	
	public Employee getEmployeeById(Long id);
	
	public List<Employee> getAllEmployees();

}
