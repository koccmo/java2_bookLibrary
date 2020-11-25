package internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.add_product_to_cart.AddProductToCartRequest;
import internet_store.core.response.add_product_to_cart.AddProductToCartResponse;

import java.math.BigDecimal;

public class AddProductToCartConsole {
    public void addToCart() {
        AddProductToCartMenu addProductToCartMenu = new AddProductToCartMenu();
        addProductToCartMenu.showMenuAddToCarProduct();
        long productId = addProductToCartMenu.getUserProductIdInput();

        SetQuantityMenu setQuantityMenu = new SetQuantityMenu();
        setQuantityMenu.showMenuSetQuantityProduct();
        BigDecimal userQuantityProduct = new BigDecimal(String.valueOf(setQuantityMenu.getUserQuantityInput()));

        AddProductToCartRequest addToCartRequest = new AddProductToCartRequest(productId, userQuantityProduct);
        AddProductToCartResponse response = ProductListApplication.addToCartService.execute(addToCartRequest);

        if (!(response.hasErrors())) {
            System.out.println("Add product to cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}