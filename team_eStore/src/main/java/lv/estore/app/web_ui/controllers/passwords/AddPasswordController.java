package lv.estore.app.web_ui.controllers.passwords;

import lv.estore.app.core.request.passwords.PasswordRequest;
import lv.estore.app.core.responses.passwords.PasswordResponse;
import lv.estore.app.core.services.passwords.AddPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPasswordController {

    @Autowired
    private AddPasswordService service;

    @GetMapping(value = "passwords/addPassword")
    public String showAddPasswordPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new PasswordRequest());
        return "passwords/addPassword";
    }

    @PostMapping("passwords/addPassword")
    public String processAddPasswordRequest(@ModelAttribute(value = "request") PasswordRequest request, ModelMap modelMap) {
        PasswordResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "passwords/addPassword";
        } else {
            return "redirect:/passwords";
        }
    }
}
