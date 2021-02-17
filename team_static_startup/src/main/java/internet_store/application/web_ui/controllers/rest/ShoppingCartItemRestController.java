package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.requests.shopping_cart_item.GetShoppingCartItemRequest;
import internet_store.application.core.responses.shopping_cart_item.AddShoppingCartItemResponse;
import internet_store.application.core.responses.shopping_cart_item.GetShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.AddShoppingCartItemService;
import internet_store.application.core.services.shopping_cart_item.GetShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product_shopping_cart")
public class ShoppingCartItemRestController {

    @Autowired private GetShoppingCartItemService getShoppingCartItemService;
    @Autowired private AddShoppingCartItemService addShoppingCartItemService;
//    @Autowired private UpdateShoppingCartItemService updateShoppingCartItemService;
//    @Autowired private DeleteShoppingCartItemService deleteShoppingCartItemService;


    @GetMapping(path = "/{id}", produces = "application/json")
    public GetShoppingCartItemResponse getShoppingCartItem(@PathVariable Long id) {
        GetShoppingCartItemRequest request = new GetShoppingCartItemRequest(id);
        return getShoppingCartItemService.execute(request);
    }

    @PostMapping(path = "/",
            produces = "application/json",
            consumes = "application/json")
    public AddShoppingCartItemResponse addShoppingCartItem(@RequestBody AddShoppingCartItemRequest request){
        return addShoppingCartItemService.execute(request);
    }

//    @PutMapping(path = "/",
//            produces = "application/json",
//            consumes = "application/json")
//    public UpdateShoppingCartItemResponse updateShoppingCartItem(@RequestBody UpdateShoppingCartItemRequest request){
//        return updateShoppingCartItemService.execute(request);
//    }
//
//    @DeleteMapping(path = "/{id}", produces = "application/json")
//    public DeleteShoppingCartItemResponse deleteShoppingCartItem(@PathVariable Long id){
//        DeleteShoppingCartItemRequest request = new DeleteShoppingCartItemRequest(id);
//        return deleteShoppingCartItemService.execute(request);
//    }


}
