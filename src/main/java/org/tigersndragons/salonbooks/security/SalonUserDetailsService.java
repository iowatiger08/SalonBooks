package org.tigersndragons.salonbooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.repository.EmployeeRepository;

@Service
public class SalonUserDetailsService implements UserDetailsService {

  @Autowired private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Employee employee =
        employeeRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    return User.withUsername(employee.getUsername())
        .password(employee.getPassword())
        .roles("USER")
        .build();
  }
}
