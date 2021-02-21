package lv.javaguru.app.web_ui.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {


	@GetMapping("/user")
	public String user () {
		return ("redirect:/admin_mode");
		//("<h1>Welcome User</h1>");
	}

	@GetMapping("/admin")
	public String admin () {
		if (isAuthenticated())
			return ("redirect:/admin_mode");
		return ("redirect:/");
	}


	private boolean isAuthenticated () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.
				isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}
}
