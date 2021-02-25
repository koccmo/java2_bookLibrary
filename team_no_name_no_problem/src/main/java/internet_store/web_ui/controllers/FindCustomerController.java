package internet_store.web_ui.controllers;


import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.services.customer.FindCustomerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindCustomerController {

    @Autowired
    private FindCustomerByIdService findCustomerByIdService;

    @GetMapping(value = "/findCustomerById")
    public String showFindCustomerByIdPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindCustomerByIdRequest());
        return "findCustomerById";
    }

    @PostMapping("/findCustomerById")
    public String processFindCustomerByIdRequest(@ModelAttribute(value = "request") FindCustomerByIdRequest findCustomerByIdRequest, ModelMap modelMap) {
        FindCustomerByIdResponse findCustomerByIdResponse = findCustomerByIdService.execute(findCustomerByIdRequest);
        if (findCustomerByIdResponse.hasErrors()){
            modelMap.addAttribute("errors", findCustomerByIdResponse.getErrors());
        } else {
            modelMap.addAttribute("customers", findCustomerByIdResponse.getCustomers());
        }
        return "/findCustomerById";
    }
}
