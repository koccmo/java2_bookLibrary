package lv.estore.app.web_ui.controllers;

import lv.estore.app.core.services.common.ExitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExitController {

    @Autowired
    private ExitService exitService;

    @GetMapping(value = "/exit")
    public String exitApp(ModelMap modelMap) {
        exitService.execute();
        return "redirect:/";
    }

}
