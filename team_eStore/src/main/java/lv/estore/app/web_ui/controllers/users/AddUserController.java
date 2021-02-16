package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.AddUserRequest;
import lv.estore.app.core.responses.users.AddUserResponse;
import lv.estore.app.core.services.users.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping(value = "users/addUser")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "users/addUser";
    }

    @PostMapping("users/addUser")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest request, ModelMap modelMap) {
        AddUserResponse response = addUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "users/addUser";
        } else {
            return "redirect:/users/";
        }
    }
}
