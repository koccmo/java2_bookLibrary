package internet_store.application.web_ui.controllers.product_shopping_cart;

import internet_store.application.core.requests.shopping_cart_item.FindShoppingCartItemByIdRequest;
import internet_store.application.core.responses.shopping_cart_item.FindShoppingCartItemByIdResponse;
import internet_store.application.core.services.shopping_cart_item.FindShoppingCartItemByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchShoppingCartItemByIdController {

    @Autowired private FindShoppingCartItemByIdService findShoppingCartItemByIdService;

    @GetMapping(value = "/product_shopping_cart/searchCartItemById")
    public String showFindSoppingCartItemByIdPage(ModelMap modelMap){
        modelMap.addAttribute("request", new FindShoppingCartItemByIdRequest());
        return "/product_shopping_cart/searchCartItemById";
    }

    @PostMapping("/product_shopping_cart/searchCartItemById")
    public String showResultsOfSearchShoppingCartItemById(
            @ModelAttribute(value = "request") FindShoppingCartItemByIdRequest request, ModelMap modelMap){
        FindShoppingCartItemByIdResponse response = findShoppingCartItemByIdService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("cartItem", response.getProductShoppingCart());
        }
        return "/product_shopping_cart/searchCartItemById";
    }

}
