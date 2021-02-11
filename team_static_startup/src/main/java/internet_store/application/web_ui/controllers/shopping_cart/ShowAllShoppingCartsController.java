package internet_store.application.web_ui.controllers.shopping_cart;

import internet_store.application.core.responses.shopping_cart.FindAllShoppingCartsResponse;
import internet_store.application.core.services.shopping_cart.FindAllShoppingCartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllShoppingCartsController {

    @Autowired
    private FindAllShoppingCartsService findAllShoppingCartsService;

    @GetMapping(value = "shopping_cart/showAllShoppingCarts")
    public String showAllShoppingCartsPage(ModelMap modelMap){
        FindAllShoppingCartsResponse response = findAllShoppingCartsService.execute();
        modelMap.addAttribute("shoppingCarts", response.getShoppingCarts());
        return "shopping_cart/showAllShoppingCarts";
    }

}
