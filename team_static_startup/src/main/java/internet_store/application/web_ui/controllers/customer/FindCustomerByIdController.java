package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.requests.shopping_cart_item.FindShoppingCartItemByIdRequest;
import internet_store.application.core.responses.customer.FindByCustomerIdResponse;
import internet_store.application.core.responses.shopping_cart_item.FindShoppingCartItemByIdResponse;
import internet_store.application.core.services.customer.FindByCustomerIdService;
import internet_store.application.core.services.shopping_cart_item.FindShoppingCartItemByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindCustomerByIdController {

    @Autowired private FindByCustomerIdService findByCustomerIdService;

    @GetMapping(value = "/customer/findCustomerById")
    public String showFindCustomerByIdPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindByCustomerIdRequest());
        return "/customer/findCustomerById";
    }

    @PostMapping("/customer/findCustomerById")
    public String showResultsOfFindCustomerById(
            @ModelAttribute(value = "request") FindByCustomerIdRequest request, ModelMap modelMap){
        FindByCustomerIdResponse response = findByCustomerIdService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("customers", response.getCustomerFindById().orElse(null));
        }
        return "/customer/findCustomerById";
    }

}
