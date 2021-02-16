package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.requests.customer.FindByCustomerFirstNameRequest;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.responses.customer.FindByCustomerFirstNameResponse;
import internet_store.application.core.services.customer.FindByCustomerFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindCustomersByFirstNameController {

    @Autowired
    FindByCustomerFirstNameService findByCustomerFirstNameService;

    @GetMapping(value = "customer/findCustomerByFirstName")
    private String showFindCustomerByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FindByCustomerFirstNameRequest());
        return "customer/findCustomerByFirstName";
    }

    @PostMapping("/customer/findCustomerByFirstName")
    public String processAddCustomerRequest(@ModelAttribute(value = "request") FindByCustomerFirstNameRequest request, ModelMap modelMap) {
        FindByCustomerFirstNameResponse response = findByCustomerFirstNameService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "customer/findCustomerByFirstName";
        } else {
            modelMap.addAttribute("customers", response.getCustomersFoundByFirstName());
            return "customer/findCustomerByFirstName";
        }
    }

}
