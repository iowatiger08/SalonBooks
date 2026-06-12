package org.tigersndragons.salonbooks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByUsernameAndPassword(String username, String password);
}
