package lesson_3.user_interface.client_menu.add_to_cart_menu;

import lesson_3.ProductListApplication;
import lesson_3.core.request.add_product_to_cart.AddProductToCartRequest;
import lesson_3.core.response.add_product_to_cart.AddProductToCartResponse;

public class AddProductToCartConsole {
    public void addToCart() {
        AddProductToCartMenu addProductToCartMenu = new AddProductToCartMenu();
        addProductToCartMenu.showMenuAddToCarProduct();
        long productId = addProductToCartMenu.getUserProductIdInput();

        SetQuantityMenu setQuantityMenu = new SetQuantityMenu();
        setQuantityMenu.showMenuSetQuantityProduct();
        int userQuantityProduct = setQuantityMenu.getUserQuantityInput();

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