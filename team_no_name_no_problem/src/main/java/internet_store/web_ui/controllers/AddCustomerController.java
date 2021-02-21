package internet_store.web_ui.controllers;


import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.services.customer.AddCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddCustomerController {

    @Autowired
    private AddCustomerService addCustomerService;

    @GetMapping(value = "/addCustomer")
    public String showAddCustomerPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddCustomerRequest());
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String processAddCustomerRequest(@ModelAttribute(value = "request") AddCustomerRequest addCustomerRequest, ModelMap modelMap) {
        AddCustomerResponse addCustomerResponse = addCustomerService.execute(addCustomerRequest);
        if (addCustomerResponse.hasErrors()){
            modelMap.addAttribute("errors", addCustomerResponse.getErrors());
            return "addCustomer";
        } else {
            return "index";
        }
    }
}
