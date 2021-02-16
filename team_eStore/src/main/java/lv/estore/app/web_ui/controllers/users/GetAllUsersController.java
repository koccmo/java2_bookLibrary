package lv.estore.app.web_ui.controllers.users;

import lv.estore.app.core.request.users.GetAllUsersRequest;
import lv.estore.app.core.responses.users.GetUsersResponse;
import lv.estore.app.core.services.users.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllUsersController {

    @Autowired private GetAllUsersService service;


    @GetMapping(value = "users/getAllUsers")
    public String showAllUsers(ModelMap modelMap) {
        GetUsersResponse response = service.execute(
                new GetAllUsersRequest()
        );
        modelMap.addAttribute("users", response.getUsers());
        return "users/getAllUsers";
    }
}
