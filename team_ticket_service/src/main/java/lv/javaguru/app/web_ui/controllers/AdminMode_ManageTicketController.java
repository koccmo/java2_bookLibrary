package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.*;
import lv.javaguru.app.core.response.*;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.database.repository.TicketRepository;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminMode_ManageTicketController {

	@Autowired
	private TicketDeleteService ticketDeleteService;

	@Autowired
	private ShowTicketByIdService showTicketByIdService;


	@GetMapping(value = "/admin_mode/manage/ticket/menu")
	public String showManageTicketPage () {
		return "admin_mode/manage/ticket/menu";
	}


	@GetMapping(value = "/admin_mode/manage/ticket/delete")
	public String showDeleteTicketPage (ModelMap modelMap) {

		modelMap.addAttribute("request", new DeleteTicketRequest());

		return "admin_mode/manage/ticket/delete";
	}


	@PostMapping("/admin_mode/manage/ticket/delete")
	public String processDeleteUsersRequest (@ModelAttribute(value = "request") DeleteTicketRequest request, ModelMap modelMap) {
		DeleteTicketResponse response = ticketDeleteService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/admin_mode/manage/ticket/delete";
		}
		else {
			return "redirect:/admin_mode/manage/ticket/menu";
		}
	}


	@GetMapping(value = "/admin_mode/manage/ticket/edit")
	public String showEditTicketPage () {
		return "admin_mode/manage/ticket/edit";
	}


	@GetMapping(value = "/admin_mode/manage/ticket/show")
	public String showShowTicketPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new ShowTicketByIdRequest());

		return "admin_mode/manage/ticket/show";
	}


	@GetMapping("/admin_mode/manage/ticket/showTicketById")
	public String processShowTicketRequest (@ModelAttribute(value = "request") ShowTicketByIdRequest request, ModelMap modelMap) {
		ShowTicketByIdResponse response = showTicketByIdService.execute(request);
		if (response.getTicket() != null) {
			modelMap.addAttribute("ticket", response.getTicket().toString());
			return "/admin_mode/manage/ticket/showTicketById";
		}
		else {
			return "redirect:/admin_mode/manage/ticket/menu";
		}
	}
}
