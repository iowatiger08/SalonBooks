package org.tigersndragons.salonbooks.core.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tigersndragons.salonbooks.model.flows.LoginFlowActions;
import org.tigersndragons.salonbooks.service.flow.LoginService;

@Controller
public class LoginController {
  @Autowired private LoginService loginService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String displayLogin(Model model) {
    model.addAttribute("loginFlowActions", new LoginFlowActions());
    return "login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String showLogin(LoginFlowActions loginFlowActions) {

    //       ModelAndView mav = new ModelAndView("login");
    //       mav.addObject("loginFlowActions",new LoginFlowActions());
    return "login";
  }

  @ModelAttribute("loginFlowActions")
  public LoginFlowActions getLoginFlowActions() {
    return new LoginFlowActions();
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String doLogin(
      @ModelAttribute("loginFlowActions") LoginFlowActions loginFlowActions,
      BindingResult result,
      ModelAndView model) {
    if (!result.hasErrors()) {
      model.addObject("employee", loginService.doLogin());
      return "home";
    } else {
      return "login";
    }
  }

  private String getErrorMessage(HttpServletRequest request, String key) {

    Exception exception = (Exception) request.getSession().getAttribute(key);

    String error = "Invalid username or password? " + exception.getMessage();

    return error;
  }
  //  public void setLoginService(LoginService loginService) {
  //    this.loginService = loginService;
  //  }

}
