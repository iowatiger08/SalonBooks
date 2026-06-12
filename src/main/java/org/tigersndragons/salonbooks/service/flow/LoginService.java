package org.tigersndragons.salonbooks.service.flow;

import org.tigersndragons.salonbooks.model.Employee;
import org.tigersndragons.salonbooks.model.flows.LoginFlowActions;

public interface LoginService {
  Employee checkEmployee(String user, String pswd);

  Employee doLogin();

  Employee doLogin(LoginFlowActions loginFlowActions);
}
