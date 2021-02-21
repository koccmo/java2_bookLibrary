package java2.application_target_list.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMenuController {

    @GetMapping(value = "/admin_menu")
    public String showAdminMenuPage (ModelMap modelMap) {
        return "admin_menu";
    }


}
