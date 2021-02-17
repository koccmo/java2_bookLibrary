package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.shopping_cart_item.GetShoppingCartItemRequest;
import internet_store.application.core.responses.shopping_cart_item.GetShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.GetShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product_shopping_cart")
public class ShoppingCartItemRestController {

    @Autowired
    private GetShoppingCartItemService getShoppingCartItemService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetShoppingCartItemResponse getShoppingCartItem(@PathVariable Long id) {
        GetShoppingCartItemRequest request = new GetShoppingCartItemRequest(id);
        return getShoppingCartItemService.execute(request);
    }

}
