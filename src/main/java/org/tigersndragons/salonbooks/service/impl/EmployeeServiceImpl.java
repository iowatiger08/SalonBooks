package org.tigersndragons.salonbooks.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.repository.EmployeeRepository;
import org.tigersndragons.salonbooks.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

  private static final long serialVersionUID = 1L;

  @Autowired private EmployeeRepository employeeRepository;

  public Employee getDefaultEmployee() {
    Employee emp = new Employee();
    emp.setId(0L);
    return emp;
  }

  public Employee getEmployee(String uname, String pwrd) {
    return getDefaultEmployee();
  }

  public Employee getEmployeeById(Long id) {
    ServiceUtils.assertNotNull("ID cannot be null", id);
    return employeeRepository.findById(id).orElse(null);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
}
