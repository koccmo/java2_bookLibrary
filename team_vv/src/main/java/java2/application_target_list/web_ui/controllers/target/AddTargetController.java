package java2.application_target_list.web_ui.controllers.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTargetController {

    @Autowired private AddTargetService addTargetService;

    @GetMapping(value = "/target/addTargetToList")
    public String showAddTargetPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddTargetRequest());
        return "target/addTargetToList";
    }

    @PostMapping("/target/addTargetToList")
    public String processAddTargetRequest(@ModelAttribute(value = "request") AddTargetRequest addTargetRequest,
                                          ModelMap modelMap) {
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest);

        if (addTargetResponse.hasErrors()) {
            modelMap.addAttribute("errors", addTargetResponse.getErrorList());
            return "target/addTargetToList";
        } else {
            return "redirect:/";
        }
    }
}
