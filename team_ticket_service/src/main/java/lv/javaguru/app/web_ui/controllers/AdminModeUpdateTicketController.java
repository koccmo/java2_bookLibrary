package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.ticket_update.UpdateTicketDestination;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketOrigin;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketSeat;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.response.TicketUpdateResponse;
import lv.javaguru.app.core.services.FlightEditService;
import lv.javaguru.app.core.services.TicketEditService;
import lv.javaguru.app.database.repository.TicketRepository;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminModeUpdateTicketController {

	@Autowired
	private TicketEditService ticketEditService;


	@GetMapping(value = "/admin_mode/manage/ticket/edit/origin")
	public String editTicketOriginPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketOrigin());

		return "/admin_mode/manage/ticket/edit/origin";
	}


	@PostMapping("/admin_mode/manage/ticket/edit/origin")
	public String processUpdateTicketOriginRequest (@ModelAttribute(value = "request") UpdateTicketOrigin request, ModelMap modelMap) {
		TicketUpdateResponse response = ticketEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/admin_mode/manage/ticket/edit/origin";
		}
		else {
			return "redirect:/admin_mode/manage/ticket/edit";
		}
	}


	@GetMapping(value = "/admin_mode/manage/ticket/edit/destination")
	public String editTicketDestinationPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketDestination());

		return "admin_mode/manage/ticket/edit/destination";
	}


	@PostMapping("/admin_mode/manage/ticket/edit/destination")
	public String processUpdateTicketDestinationRequest (@ModelAttribute(value = "request") UpdateTicketDestination request, ModelMap modelMap) {
		TicketUpdateResponse response = ticketEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "admin_mode/manage/ticket/edit/destination";
		}
		else {
			return "redirect:/admin_mode/manage/ticket/edit";
		}
	}


	@GetMapping(value = "/admin_mode/manage/ticket/edit/seat")
	public String editTicketSeatPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateTicketSeat());

		return "admin_mode/manage/ticket/edit/seat";
	}


	@PostMapping("/admin_mode/manage/ticket/edit/seat")
	public String processUpdateTicketSeatRequest (@ModelAttribute(value = "request") UpdateTicketSeat request, ModelMap modelMap) {
		TicketUpdateResponse response = ticketEditService.execute(request);

		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/admin_mode/manage/ticket/edit/seat";
		}
		else {
			return "redirect:/admin_mode/manage/ticket/edit";
		}
	}


}
