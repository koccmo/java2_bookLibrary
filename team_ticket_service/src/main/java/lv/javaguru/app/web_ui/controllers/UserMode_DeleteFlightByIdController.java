package lv.javaguru.app.web_ui.controllers;


import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.core.services.FlightDeleteService;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserMode_DeleteFlightByIdController {

	@Autowired
	private FlightDeleteService flightDeleteService;
	@Autowired
	private UserRepository userRepository;

	User user;


	@GetMapping(value = "/user_mode/deleteFlight")
	public String showAddProductPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new DeleteFlightRequest());

		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		user = userRepository.getUserByUsername(currentUser);

		return "user_mode/deleteFlight";
	}


	@PostMapping("/user_mode/deleteFlight")
	public String deleteProduct (@ModelAttribute(value = "request") DeleteFlightRequest request, ModelMap modelMap) {

		request.setUser(user);

		FlightDeleteResponse response = flightDeleteService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "user_mode/deleteFlight";
		}
		else {
			return "redirect:/user_mode";
		}
	}

}
