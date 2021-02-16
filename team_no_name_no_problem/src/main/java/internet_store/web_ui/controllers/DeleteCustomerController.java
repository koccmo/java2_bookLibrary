package internet_store.web_ui.controllers;


import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.customer.DeleteCustomerResponse;
import internet_store.core.services.customer.DeleteCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteCustomerController {

    @Autowired
    private DeleteCustomerService deleteCustomerService;

    @GetMapping(value = "/deleteCustomer")
    public String showDeleteCustomerPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteCustomerRequest());
        return "deleteCustomer";
    }

    @PostMapping("/deleteCustomer")
    public String processDeleteCustomerRequest(@ModelAttribute(value = "request") DeleteCustomerRequest deleteCustomerRequest, ModelMap modelMap) {
        DeleteCustomerResponse deleteCustomerResponse = deleteCustomerService.execute(deleteCustomerRequest);
        if (deleteCustomerResponse.hasErrors()){
            modelMap.addAttribute("errors", deleteCustomerResponse.getErrors());
            return "/deleteCustomer";
        } else {
            return "index";
        }
    }
}
