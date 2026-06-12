package org.tigersndragons.salonbooks.service.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.flows.LoginFlowActions;
import org.tigersndragons.salonbooks.service.EmployeeService;
import org.tigersndragons.salonbooks.service.flow.LoginService;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class LoginServiceImpl implements LoginService, MessageSourceAware {
	@Autowired
	private EmployeeService employeeService;
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource= messageSource;
	}

	public Employee checkEmployee(String user, String pswd) {
		return employeeService.getEmployee(user, pswd);
	}

	public Employee doLogin() {
		// TODO actually get employee
		return employeeService.getDefaultEmployee();
	}
	public Employee doLogin(LoginFlowActions loginFlowActions) {

		return doLogin();
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
