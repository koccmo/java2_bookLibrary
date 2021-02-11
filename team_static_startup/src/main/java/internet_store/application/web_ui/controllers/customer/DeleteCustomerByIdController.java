package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.requests.customer.DeleteByCustomerIdRequest;
import internet_store.application.core.responses.customer.DeleteByCustomerIdResponse;
import internet_store.application.core.services.customer.DeleteByCustomerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteCustomerByIdController {

    @Autowired
    private DeleteByCustomerIdService deleteByCustomerIdService;

    @GetMapping(value = "customer/deleteCustomer")
    private String showDeleteByCustomerIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteByCustomerIdRequest());
        return "customer/deleteCustomer";
    }

    @PostMapping("customer/deleteCustomer")
    private String deleteCustomerById(
            @ModelAttribute(value = "request") DeleteByCustomerIdRequest request, ModelMap modelMap) {
        DeleteByCustomerIdResponse response = deleteByCustomerIdService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "customer/deleteCustomer";
        } else {
            return "redirect:/";
        }
    }

}
