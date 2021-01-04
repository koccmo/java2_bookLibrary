package lesson_8.mysql_spring.controller;

import lesson_8.mysql_spring.ClientDb;
import lesson_8.mysql_spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateController {
    @Autowired
    private ClientDb clientDb;

    @GetMapping(value = "/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("client", new Client());
        return "create";
    }

    @PostMapping("/create")
    public String createNewRecord(@ModelAttribute(value = "client") Client client) {
        clientDb.insertRecord(client);
        return "index";
    }
}