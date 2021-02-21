package java2.application_target_list.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserMenuController {

    @GetMapping(value = "/user_menu")
    public String showUserMenuPage (ModelMap modelMap) {
        return "user_menu";
    }

}
