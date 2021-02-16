package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.UserIdRequest;
import lv.estore.app.core.responses.users.FindUserByIdResponse;
import lv.estore.app.core.services.users.FindUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindUserByIdController {

    @Autowired
    private FindUserByIdService service;

    @GetMapping(value = "users/findUserById")
    public String showFindUserByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UserIdRequest());
        return "users/findUserById";
    }

    @PostMapping("users/findUserById")
    public String processFindUserByIdRequest(@ModelAttribute(value = "request") UserIdRequest request,
                                                ModelMap modelMap) {
        FindUserByIdResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("user", response.getUser());
        }
        return "users/findUserById";
    }
}
