package internet_store.application.web_ui.controllers.product_shopping_cart;

import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.shopping_cart_item.AddShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.AddShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class AddShoppingCartItemController {

    @Autowired
    private AddShoppingCartItemService addShoppingCartItemService;

    @GetMapping(value = "/product_shopping_cart/addShoppingCartItem")
    public String showAddShoppingCartItemPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddShoppingCartItemRequest());
        return "product_shopping_cart/addShoppingCartItem";
    }

    @PostMapping("/product_shopping_cart/addShoppingCartItem")
    public String processAddShoppingCartItemRequest(@ModelAttribute(value = "request") AddShoppingCartItemRequest request, ModelMap modelMap) {
        AddShoppingCartItemResponse response = addShoppingCartItemService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "product_shopping_cart/addShoppingCartItem";
        } else {
            return "redirect:/";
        }
    }

}
