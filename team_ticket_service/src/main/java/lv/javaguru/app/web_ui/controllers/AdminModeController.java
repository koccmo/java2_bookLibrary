package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.LogOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminModeController {

	@Autowired
	private LogOutService logOutService;

	@GetMapping(value = "/admin_mode")
	public String showAdminModePage (ModelMap modelMap) {
		//modelMap.addAttribute("request", new UserAddRequest());
		return "admin_mode";
	}

	@GetMapping(value = "/logout")
	public String showIndexPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new LogOutRequest());
		return "logout";
	}

	@PostMapping("/logout")
	public String processLogoutRequest (@ModelAttribute(value = "request") LogOutRequest request, ModelMap modelMap) {
		LogOutResponse response = logOutService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "logout";
		}
		else {
			return "redirect:/index";
		}
	}
}
