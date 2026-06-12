package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Employee;

public interface EmployeeService {

  Employee getDefaultEmployee();

  Employee getEmployee(String uname, String pwrd);

  Employee getEmployeeById(Long id);

  List<Employee> getAllEmployees();
}
