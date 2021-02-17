package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.LogInService;
import lv.javaguru.app.core.services.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInController {

	@Autowired
	private LogInService logInService;


	@GetMapping(value = "/login")
	public String showLoginPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new LogInRequest());
		return "login";
	}

	@PostMapping(value = "/login")
	public String processLogInRequest (@ModelAttribute(value = "request") LogInRequest request, ModelMap modelMap) {
		LogInResponse response = logInService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "login";
		}
		else if (response.isAdminMode()) {
			return "redirect:/admin_mode";
		} else
		//	return "login";
			return "redirect:/user_mode";
	}

}
