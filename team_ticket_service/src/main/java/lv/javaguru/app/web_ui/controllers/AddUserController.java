package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

	@Autowired private UserAddService userAddService;


	@GetMapping(value = "/register")
	public String showUserAddPage(ModelMap modelMap) {
		modelMap.addAttribute("request", new UserAddRequest());
		return "register";
	}

	//@PostMapping("/register")
	//public String doRegister(@ModelAttribute UserDto userDto) {
	//	String encodedPassword  = passwordEncoder.encode(userDto.getPassword1());
//
	//	User user = new User();
	//	user.setEnabled(Boolean.TRUE);
	//	user.setPassword(encodedPassword);
	//	user.setUsername(userDto.getUsername());
//
	//	UserAuthority boardAuthority = new UserAuthority();
	//	boardAuthority.setAuthority("BOARD");
	//	boardAuthority.setUser(user);
	//	user.getAuthorities().add(boardAuthority);
	//	userRepository.save(user);
//
	//	return "register-success";
	//}

	@PostMapping("/register")
	public String processAddUserRequest(@ModelAttribute(value = "request") UserAddRequest request, ModelMap modelMap) {
		UserAddResponse response = userAddService.execute(request);
		if (response.hasErrors()) {
			modelMap.addAttribute("errors", response.getErrorList());
			return "register";
		} else {
			return "redirect:/";
		}
	}

}
