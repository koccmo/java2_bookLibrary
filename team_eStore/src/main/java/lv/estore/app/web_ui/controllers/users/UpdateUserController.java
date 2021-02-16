package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.UserUpdateRequest;
import lv.estore.app.core.responses.users.UpdateUserResponse;
import lv.estore.app.core.services.users.UpdateUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateUserController {

    @Autowired
    private UpdateUserByIdService service;

    @GetMapping(value = "users/updateUser")
    public String showUpdateUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserUpdateRequest());
        return "users/updateUser";
    }

    @PostMapping("users/updateUser")
    public String processUpdateUserRequest(@ModelAttribute(value = "request") UserUpdateRequest request,
                                           ModelMap modelMap) {
        UpdateUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "users/updateUser";
        } else {
            return "redirect:/users/";
        }
    }
}
