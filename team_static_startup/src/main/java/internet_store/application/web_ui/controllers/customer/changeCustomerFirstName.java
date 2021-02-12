package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.responses.customer.ChangeCustomerFirstNameResponse;
import internet_store.application.core.services.customer.ChangeCustomerFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class changeCustomerFirstName {

    @Autowired
    private ChangeCustomerFirstNameService changeCustomerFirstNameService;

    @GetMapping(value = "/customer/changeCustomerFirstName")
    public String showChangeCustomerFirstNamePage(ModelMap modelMap){
        modelMap.addAttribute("request", new ChangeCustomerFirstNameRequest());
        return "customer/changeCustomerFirstName";
    }

/*    @PostMapping("/customer/changeCustomerFirstName")
    public String showChangeCustomerFirstName(
            @ModelAttribute(value = "request") ChangeCustomerFirstNameRequest request, ModelMap modelMap){
        ChangeCustomerFirstNameResponse response = ChangeCustomerFirstNameService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("customers", response.getCustomerObject());
        }
        return "customer/changeCustomerFirstName";
    }*/

}
