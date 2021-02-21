package internet_store.web_ui.controllers;


import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.SearchCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchCustomerController {

    @Autowired
    private SearchCustomerService searchCustomerService;

    @GetMapping(value = "/searchCustomer")
    public String showSearchCustomerPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchCustomerRequest());
        return "searchCustomer";
    }

    @PostMapping("/searchCustomer")
    public String processSearchCustomerRequest(@ModelAttribute(value = "request") SearchCustomerRequest request, ModelMap modelMap){
        SearchCustomerResponse response = searchCustomerService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("customers", response.getCustomers());
        }
        return "/searchProduct";
    }
}
