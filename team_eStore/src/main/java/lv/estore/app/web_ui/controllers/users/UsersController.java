package lv.estore.app.web_ui.controllers.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping(value = "/users")
    public String showUsersPage() {
        return "users/users";
    }

}
