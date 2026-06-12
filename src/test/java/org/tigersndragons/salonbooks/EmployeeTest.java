package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.service.EmployeeService;

public class EmployeeTest extends BaseTestCase {

    @Autowired
    EmployeeService employeeService;

    private Employee e1, e2;

    @BeforeEach
    public void setUp() {
        e1 = new Employee();
        e2 = new Employee();
    }

    @Test
    public void testMatchId() {
        e1.setId(0L);
        e2.setId(0L);
        assertTrue(e1.equals(e2));
        e2.setId(1L);
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testMatchingUsername() {
        e1.setId(0L);
        e1.setUsername("auser");
        e2.setId(0L);
        e2.setUsername("auser");
        assertTrue(e1.equals(e2));
        assertTrue(e1.getUsername().equals(e2.getUsername()));
        e2.setId(1L);
        assertFalse(e1.equals(e2));
        assertTrue(e1.getUsername().equals(e2.getUsername()));
    }

    private Employee theDefaultEmployee() {
        Employee emp = new Employee();
        emp.setId(0L);
        emp.setName("Default");
        emp.setUsername("default");
        emp.setPassword("password1");
        return emp;
    }

    @Test
    public void retrieveDefaultEmployee() {
        Employee emp = employeeService.getDefaultEmployee();
        assertTrue(emp.equals(theDefaultEmployee()));
    }

    @Test
    public void retrieveDefaultEmployeeByEmptyCredentials() {
        Employee emp = employeeService.getEmployee("", "");
        assertTrue(emp.equals(theDefaultEmployee()));
    }

    @Test
    public void retrieveDefaultEmployeeById() {
        Employee emp = employeeService.getEmployeeById(0L);
        Employee e2 = theDefaultEmployee();
        assertTrue(emp.equals(e2));
        assertTrue(StringUtils.equals(emp.getUsername(), e2.getUsername()));
        assertTrue(StringUtils.equals(emp.getName(), e2.getName()));
        assertTrue(StringUtils.equals(emp.getPassword(), e2.getPassword()));
    }

    @Test
    public void getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        Employee e2 = theDefaultEmployee();
        assertTrue(StringUtils.equals(employeeList.get(0).getUsername(), e2.getUsername()));
        assertTrue(StringUtils.equals(employeeList.get(0).getName(), e2.getName()));
    }
}
