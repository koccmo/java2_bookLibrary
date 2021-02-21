package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.core.services.FlightShowAllService;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowUsersFlightsController {

	@Autowired
	private FlightShowAllService flightShowAllService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "user_mode/showFlights")
	public String showUserFlights (ModelMap modelMap) {

	Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.getUserByUsername(currentUser);
		FlightShowAllRequest request = new FlightShowAllRequest(user);
		FlightShowAllResponse response = flightShowAllService.execute(request);
		modelMap.addAttribute("flights", response.getListAsString());
		return "user_mode/showFlights";
	}

}


