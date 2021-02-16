package lv.estore.app.web_ui.controllers.passwords;

import lv.estore.app.core.request.passwords.PasswordRequest;
import lv.estore.app.core.responses.passwords.PasswordResponse;
import lv.estore.app.core.services.passwords.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdatePasswordController {

    @Autowired
    private UpdatePasswordService service;

    @GetMapping(value = "passwords/updatePassword")
    public String showUpdatePasswordPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new PasswordRequest());
        return "passwords/updatePassword";
    }

    @PostMapping("passwords/updatePassword")
    public String processUpdatePasswordRequest(@ModelAttribute(value = "request") PasswordRequest request, ModelMap modelMap) {
        PasswordResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "passwords/updatePassword";
        } else {
            return "redirect:/passwords";
        }
    }
}
