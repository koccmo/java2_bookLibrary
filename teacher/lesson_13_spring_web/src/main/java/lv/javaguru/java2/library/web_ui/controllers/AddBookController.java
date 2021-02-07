package lv.javaguru.java2.library.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.services.AddBookService;

@Controller
public class AddBookController {

	@Autowired private AddBookService addBookService;


	@GetMapping(value = "/addBookToList")
	public String showAddBookPage(ModelMap modelMap) {
		modelMap.addAttribute("request", new AddBookRequest());
		return "addBookToList";
	}

	@PostMapping("/addBookToList")
	public String processAddBookRequest(@ModelAttribute(value = "request") AddBookRequest request) {
		addBookService.execute(request);
		return "index";
	}

}
