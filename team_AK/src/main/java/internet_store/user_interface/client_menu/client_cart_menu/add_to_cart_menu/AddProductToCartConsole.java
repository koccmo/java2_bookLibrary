package internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.service.cart.AddProductToCartService;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.math.BigDecimal;

@DIComponent
public class AddProductToCartConsole {
@DIDependency
AddProductToCartService addProductToCartService;

    public void addToCart() {
//        final AddProductToCartService addProductToCartService = ProductListApplication.applicationContext
//                .getBean(AddProductToCartService.class);
        AddProductToCartMenu addProductToCartMenu = new AddProductToCartMenu();
        addProductToCartMenu.showMenuAddToCarProduct();
        long productId = addProductToCartMenu.getUserProductIdInput();

        SetQuantityMenu setQuantityMenu = new SetQuantityMenu();
        setQuantityMenu.showMenuSetQuantityProduct();
        BigDecimal userQuantityProduct = new BigDecimal(String.valueOf(setQuantityMenu.getUserQuantityInput()));

        AddProductToCartRequest addToCartRequest = new AddProductToCartRequest(productId, userQuantityProduct);
        AddProductToCartResponse response = addProductToCartService.execute(addToCartRequest);

        if (!(response.hasErrors())) {
            System.out.println("Add product to cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}