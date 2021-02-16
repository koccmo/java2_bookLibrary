package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.UserEmailRequest;
import lv.estore.app.core.responses.users.RemoveUserResponse;
import lv.estore.app.core.services.users.RemoveUserByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveUserByEmailController {

    @Autowired
    private RemoveUserByEmailService service;

    @GetMapping(value = "users/removeUserByEmail")
    public String showRemoveUserByEmailPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserEmailRequest());
        return "users/removeUserByEmail";
    }

    @PostMapping("users/removeUserByEmail")
    public String processRemoveProductByNameRequest(@ModelAttribute(value = "request") UserEmailRequest request,
                                                    ModelMap modelMap) {
        RemoveUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "users/removeUserByEmail";
        } else {
            return "redirect:/users/";
        }
    }
}
