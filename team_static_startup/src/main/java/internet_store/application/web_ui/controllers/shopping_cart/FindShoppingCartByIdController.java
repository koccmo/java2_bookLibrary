package internet_store.application.web_ui.controllers.shopping_cart;

import internet_store.application.core.requests.shopping_cart.FindShoppingCartByIdRequest;
import internet_store.application.core.responses.shopping_cart.FindShoppingCartByIdResponse;
import internet_store.application.core.services.shopping_cart.FindShoppingCartByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindShoppingCartByIdController {

    @Autowired private FindShoppingCartByIdService findShoppingCartByIdService;

    @GetMapping(value = "shopping_cart/findCartById")
    public String showFindShoppingCartByIdServicePage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindShoppingCartByIdRequest());
        return "shopping_cart/findCartById";
    }

    @PostMapping("shopping_cart/findCartById")
    public String showCartById(
            @ModelAttribute( value = "request") FindShoppingCartByIdRequest request, ModelMap modelMap){
        FindShoppingCartByIdResponse response = findShoppingCartByIdService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("carts", response.getShoppingCart());
        }
        return "shopping_cart/findCartById";
    }

}
