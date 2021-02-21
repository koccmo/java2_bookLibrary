package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.request.DeleteTicketRequest;
import lv.javaguru.app.core.request.ShowTicketByIdRequest;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.request.user_update.UpdateUserUsername;
import lv.javaguru.app.core.response.*;
import lv.javaguru.app.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminMode_ManageUserController {

	@Autowired
	private UserDeleteService userDeleteService;

	@Autowired
	private UserEditService userEditService;

	@Autowired
	private UserShowSingleService userShowSingleService;


	@GetMapping(value = "/admin_mode/manage/user/menu")
	public String showManageUsersPage () {
		return "admin_mode/manage/user/menu";
	}


	@GetMapping(value = "/admin_mode/manage/user/deleteUser")
	public String showDeleteUsersPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UserDeleteRequest());

		return "admin_mode/manage/user/deleteUser";
	}


	@PostMapping("/admin_mode/manage/user/deleteUser")
	public String processDeleteUsersRequest (@ModelAttribute(value = "request") UserDeleteRequest request, ModelMap modelMap) {
		UserDeleteResponse response = userDeleteService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/admin_mode/manage/user/deleteUser";
		}
		else {
			return "redirect:/admin_mode/manage/user/menu";
		}
	}

	@GetMapping(value = "/admin_mode/manage/user/editUser")
	public String showEditUsersPage () {
		return "admin_mode/manage/user/editUser";
	}


	@GetMapping(value = "/admin_mode/manage/user/edit/username")
	public String showEditUsernamePage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UpdateUserUsername());

		return "admin_mode/manage/user/edit/username";
	}

	@PostMapping("/admin_mode/manage/user/edit/username")
	public String processEditUsernameRequest (@ModelAttribute(value = "request") UpdateUserUsername request, ModelMap modelMap) {
		UserEditResponse response = userEditService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "/admin_mode/manage/user/edit/username";
		}
		else {
			return "redirect:/admin_mode/manage/user/editUser";
		}
	}


	@GetMapping(value = "/admin_mode/manage/user/showUser")
	public String showShowUsersPage (ModelMap modelMap) {
		modelMap.addAttribute("request", new UserShowSingleRequest());

		return "admin_mode/manage/user/showUser";
	}


	@GetMapping("/admin_mode/manage/user/showUserById")
	public String processShowUsersRequest (@ModelAttribute(value = "request") UserShowSingleRequest request, ModelMap modelMap) {
		UserShowSingleResponse response = userShowSingleService.execute(request);
		if (response.getUser() != null) {
			modelMap.addAttribute("user", response.getUser());
			return "/admin_mode/manage/user/showUserById";
		}
		else {
			return "redirect:/admin_mode/manage/user/menu";
		}
	}
}
