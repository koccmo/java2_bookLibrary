package lv.javaguru.app.web_ui.controllers;


import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.core.services.FlightDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteFlightByIdController {

    @Autowired
    private FlightDeleteService flightDeleteService;

    @GetMapping("/user_mode/deleteFlight")
    public String showAddProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteFlightRequest());
        return "user_mode/deleteFlight";
    }

    @PostMapping(value = "/user_mode/deleteFlight")
    public String deleteProduct(@ModelAttribute(value = "request") DeleteFlightRequest request, ModelMap modelMap) {
        FlightDeleteResponse response = flightDeleteService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "user_mode/deleteFlight";
        } else {
            return "redirect:/user_mode";
        }
    }

}
