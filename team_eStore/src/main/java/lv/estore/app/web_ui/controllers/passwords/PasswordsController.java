package lv.estore.app.web_ui.controllers.passwords;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordsController {

    @GetMapping(value = "/passwords")
    public String showPasswordsPage() {
        return "passwords/passwords";
    }

}
