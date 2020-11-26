package internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.update_cart_request.UpdateCartRequest;
import internet_store.core.response.update_cart_response.UpdateCartResponse;

import java.math.BigDecimal;

public class UpdateCartConsole {

    public void updateCart() {
        UpdateCartIdMenu updateCartIdMenu = new UpdateCartIdMenu();
        UpdateCartQuantityMenu updateCartQuantityMenu = new UpdateCartQuantityMenu();

        updateCartIdMenu.showMenuUpdateCart();
        long productId = updateCartIdMenu.getUserUpdatedCartIdInput();
        updateCartQuantityMenu.showMenuUpdateQuantityCart();
        BigDecimal newQuantity = new BigDecimal(String.valueOf(updateCartQuantityMenu.getUserUpdatedProductQuantityInput()));

        UpdateCartRequest request = new UpdateCartRequest(productId, newQuantity);
        UpdateCartResponse response = ProductListApplication.updateCartService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Product updated in cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}