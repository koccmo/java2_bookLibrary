package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.UserIdRequest;
import lv.estore.app.core.responses.users.RemoveUserResponse;
import lv.estore.app.core.services.users.RemoveUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveUserByIdController {

    @Autowired
    private RemoveUserByIdService service;

    @GetMapping(value = "users/removeUserById")
    public String showRemoveUserByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserIdRequest());
        return "users/removeUserById";
    }

    @PostMapping("users/removeUserById")
    public String processRemoveUserByIdRequest(@ModelAttribute(value = "request") UserIdRequest request,
                                                    ModelMap modelMap) {
        RemoveUserResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "users/removeUserById";
        } else {
            return "redirect:/users/";
        }
    }
}
