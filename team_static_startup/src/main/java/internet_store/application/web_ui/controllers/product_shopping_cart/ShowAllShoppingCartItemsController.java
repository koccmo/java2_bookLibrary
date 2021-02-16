package internet_store.application.web_ui.controllers.product_shopping_cart;

import internet_store.application.core.requests.shopping_cart_item.FindAllShoppingCartItemsRequest;
import internet_store.application.core.responses.shopping_cart.FindAllShoppingCartsResponse;
import internet_store.application.core.responses.shopping_cart_item.FindAllShoppingCartItemsResponse;
import internet_store.application.core.services.shopping_cart.FindAllShoppingCartsService;
import internet_store.application.core.services.shopping_cart_item.FindAllShoppingCartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllShoppingCartItemsController {

    @Autowired
    private FindAllShoppingCartItemsService findAllShoppingCartItemsService;

    @GetMapping(value = "product_shopping_cart/showAllShoppingCartItems")
    public String showAllShoppingCartItemsPage(ModelMap modelMap){
        FindAllShoppingCartItemsResponse response = findAllShoppingCartItemsService.execute(new FindAllShoppingCartItemsRequest());
        modelMap.addAttribute("shoppingCartItems", response.getProductShoppingCartList());
        return "product_shopping_cart/showAllShoppingCartItems";
    }

}
