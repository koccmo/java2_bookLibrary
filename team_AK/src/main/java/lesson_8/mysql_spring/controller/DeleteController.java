package lesson_8.mysql_spring.controller;

import lesson_8.mysql_spring.ClientDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {
    @Autowired
    private ClientDb clientDb;

    @GetMapping(value = "/delete")
    public String delete(ModelMap modelMap) {
        modelMap.addAttribute("error", "");
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam(value = "id") String idFromUser, ModelMap modelMap) {
        long id;
        try {
            id = Long.parseLong(idFromUser);
            if (!(clientDb.isRecordExist(id))) {
                modelMap.addAttribute("error", "Record no exist Try again");
                return "delete";
            }
        } catch (NumberFormatException e) {
            modelMap.addAttribute("error", "Record no exist Try again");
            return "delete";
        }
        clientDb.deleteRecord(id);
        return "index";
    }
}