package lesson_8.mysql_spring.controller;

import lesson_8.mysql_spring.ClientDb;
import lesson_8.mysql_spring.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {
    @Autowired
    private ClientDb clientDb;

    @GetMapping(value = "/update")
    public String update(ModelMap modelMap) {
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("error", "");
        return "update";
    }

    @PostMapping("/update")
    public String updateRecord(@RequestParam String idFromUser,
                               @ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        long id;
        try {
            id = Long.parseLong(idFromUser);
            if (!(clientDb.isRecordExist(id))) {
                modelMap.addAttribute("error", "Record no exist Try again");
                return "update";
            }
        } catch (NumberFormatException e) {
            modelMap.addAttribute("error", "Record no exist Try again");
            return "update";
        }
        clientDb.updateRecord(id, client);
        return "index";
    }
}