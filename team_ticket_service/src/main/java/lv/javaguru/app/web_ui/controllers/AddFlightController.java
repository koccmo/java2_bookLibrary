package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.response.AddFlightResponse;
import lv.javaguru.app.core.services.FlightAddService;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddFlightController {

	@Autowired
	private FlightAddService flightAddService;
	@Autowired
	private UserRepository userRepository;

	User user;
	@GetMapping(value = "/user_mode/addFlight")
	public String addFlightPage (ModelMap modelMap) {

		modelMap.addAttribute("ticket", new Ticket());
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		user = userRepository.getUserByUsername(currentUser);
		modelMap.addAttribute("user", user);
		return "user_mode/addFlight";
	}

	@PostMapping("/user_mode/addFlight")
	public String processAddFlightPageRequest (@ModelAttribute(value = "ticket") Ticket ticket, ModelMap modelMap) {
		AddFlightRequest request = new AddFlightRequest(user, ticket);

		AddFlightResponse response = flightAddService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "user_mode/addFlight";
		}
		else {
			return "redirect:/user_mode";
		}
	}
}
