package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.UserNameRequest;
import lv.estore.app.core.responses.users.GetUsersResponse;
import lv.estore.app.core.services.users.FindUsersByLastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindUsersByLastNameController {

    @Autowired
    private FindUsersByLastNameService service;

    @GetMapping(value = "users/findUsersByLastName")
    public String showFindUsersByLastNamePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserNameRequest());
        return "users/findUsersByLastName";
    }

    @PostMapping("users/findUsersByLastName")
    public String processFindUsersByLastNameRequest(@ModelAttribute(value = "request") UserNameRequest request,
                                           ModelMap modelMap) {
        GetUsersResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("users", response.getUsers());
        }
        return "users/findUsersByLastName";
    }
}
