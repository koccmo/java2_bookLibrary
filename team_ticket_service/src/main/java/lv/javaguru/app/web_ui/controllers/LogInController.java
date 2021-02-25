package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.LogInService;
import lv.javaguru.app.core.services.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogInController {

	@GetMapping(value = "/user_mode")
	public String showUserModePage(ModelMap modelMap) {
	//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	//	String currentPrincipalName = authentication.getName();
		//modelMap.addAttribute("user", currentPrincipalName);
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

// put the UserDetails object here.
		modelMap.addAttribute("user", currentUser);
		return "user_mode";
	}
	//@Autowired
	//private LogInService logInService;


//@GetMapping(value = "/login")
//public String showLoginPage (ModelMap modelMap) {
//	modelMap.addAttribute("request", new LogInRequest());
//	return "login";
//}

//@PostMapping(value = "/login")
//public String processLogInRequest (@ModelAttribute(value = "request") LogInRequest request, ModelMap modelMap) {
//	LogInResponse response = logInService.execute(request);
//	if (response.hasErrors()) {
//		modelMap.addAttribute("errors", response.getErrorList());
//		return "login";
//	}
//	else if (response.isAdminMode()) {
//		return "redirect:/admin_mode";
//	} else
//	//	return "login";
//		return "redirect:/user_mode";
//}

}
