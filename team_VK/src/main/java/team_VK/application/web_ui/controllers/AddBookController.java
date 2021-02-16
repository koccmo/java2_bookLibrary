package team_VK.application.web_ui.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.services.main_menu_services.AddBookService;

@Controller
public class AddBookController {

    @Autowired
    private AddBookService addBookService;


    @GetMapping(value = "/addBookToList")
    public String showAddBookPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddBookRequest());
        return "addBookToList";
    }

    @PostMapping("/addBookToList")
    public String processAddBookRequest(@ModelAttribute(value = "request") AddBookRequest request) {
        addBookService.addBook(request);
        return "index";
    }

}