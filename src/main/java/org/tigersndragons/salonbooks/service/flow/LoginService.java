package org.tigersndragons.salonbooks.service.flow;

import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.flows.LoginFlowActions;

public interface LoginService {
	public Employee checkEmployee (String user, String pswd);
	public Employee doLogin();
	public Employee doLogin(LoginFlowActions loginFlowActions);
}
