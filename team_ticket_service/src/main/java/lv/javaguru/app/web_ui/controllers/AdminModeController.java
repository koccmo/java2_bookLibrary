package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.*;
import lv.javaguru.app.core.request.user_update.UpdateUserUsername;
import lv.javaguru.app.core.response.*;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminModeController {

	@Autowired
	private LogOutService logOutService;


	@Autowired
	private UserShowAllService userShowAllService;

	@Autowired
	private FlightShowAllService flightShowAllService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/admin_mode")
	public String showAdminModePage () {
		return "admin_mode";
	}





	@GetMapping(value = "/admin_mode/showAllFlights")
	public String showAllFlights (ModelMap modelMap) {
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.getUserByUsername(currentUser);

		FlightShowAllRequest request = new FlightShowAllRequest(user);
		FlightShowAllResponse response = flightShowAllService.execute(request);
		modelMap.addAttribute("flights", response.getListAsString());

		return "admin_mode/showAllFlights";
	}

	@GetMapping(value = "/admin_mode/showAllUsers")
	public String showAllUsers (ModelMap modelMap) {
		Long id = null;
		UserShowAllRequest request = new UserShowAllRequest(id);

		UserShowAllResponse<?> response = userShowAllService.execute(request);
		modelMap.addAttribute("users", response.getResponseAsString());
		return "admin_mode/showAllUsers";
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
