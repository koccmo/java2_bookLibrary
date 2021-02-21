package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketDestination;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketOrigin;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketSeat;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.FlightEditService;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateFlightController {


	@Autowired
	private FlightEditService flightEditService;
	@Autowired
	private UserRepository userRepository;


	@GetMapping(value = "/user_mode/editTicket")
	public String editTicketMenuPage () {
		return "user_mode/editTicket";
	}

	@GetMapping(value = "/user_mode/editTicket/editTicketOrigin")
	public String editTicketOriginPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketOrigin());

		return "user_mode/editTicket/editTicketOrigin";
	}

	@PostMapping("/user_mode/editTicket/editTicketOrigin")
	public String processUpdateTicketOriginRequest (@ModelAttribute(value = "request") UpdateTicketOrigin request, ModelMap modelMap) {
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/user_mode/editTicket/editTicketOrigin";
		}
		else {
			return "redirect:/user_mode/editTicket";
		}
	}


	@GetMapping(value = "/user_mode/editTicket/editTicketDestination")
	public String editTicketDestinationPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketDestination());

		return "user_mode/editTicket/editTicketDestination";
	}

	@PostMapping("/user_mode/editTicket/editTicketDestination")
	public String processUpdateTicketDestinationRequest (@ModelAttribute(value = "request") UpdateTicketDestination request, ModelMap modelMap) {
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/user_mode/editTicket/editTicketDestination";
		}
		else {
			return "redirect:/user_mode/editTicket";
		}
	}

	@GetMapping(value = "/user_mode/editTicket/editTicketSeat")
	public String editTicketSeatPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketSeat());

		return "user_mode/editTicket/editTicketSeat";
	}

	@PostMapping("/user_mode/editTicket/editTicketSeat")
	public String processUpdateTicketSeatRequest (@ModelAttribute(value = "request") UpdateTicketSeat request, ModelMap modelMap) {
		FlightEditResponse response = flightEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/user_mode/editTicket/editTicketSeat";
		}
		else {
			return "redirect:/user_mode/editTicket";
		}
	}


}
