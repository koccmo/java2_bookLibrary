package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.UserAddRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserModeController {

	@GetMapping(value = "/user_mode")
	public String showUserModePage(ModelMap modelMap) {
	//	modelMap.addAttribute("request", new UserAddRequest());
		return "user_mode";
	}
}
