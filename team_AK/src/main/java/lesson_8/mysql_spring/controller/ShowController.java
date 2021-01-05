package lesson_8.mysql_spring.controller;

import lesson_8.mysql_spring.ClientDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowController {
    @Autowired
    private ClientDb clientDb;

    @GetMapping(value = "/show")
    public String show(ModelMap modelMap) {
        modelMap.addAttribute("clientList", clientDb.findAllRecords());
        return "show";
    }
}