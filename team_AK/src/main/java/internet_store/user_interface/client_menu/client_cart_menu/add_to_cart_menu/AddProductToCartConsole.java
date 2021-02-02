package internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu;

import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.database.cart_database.CartDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddProductToCartConsole {
    @Autowired
    AddToCartService addToCartService;
    @Autowired
    CartDatabaseImpl cartDatabase;

    public void addToCart() {
        AddProductToCartMenu addProductToCartMenu = new AddProductToCartMenu();
        addProductToCartMenu.showMenuAddToCarProduct();
        long productId = addProductToCartMenu.getUserProductIdInput();

        SetQuantityMenu setQuantityMenu = new SetQuantityMenu();
        setQuantityMenu.showMenuSetQuantityProduct();
        long userQuantityProduct = setQuantityMenu.getUserQuantityInput();

        AddProductToCartRequest addToCartRequest = new AddProductToCartRequest(productId, userQuantityProduct, cartDatabase, "");
        AddProductToCartResponse response = addToCartService.execute(addToCartRequest);

        if (!(response.hasErrors())) {
            System.out.println("Add product to cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}