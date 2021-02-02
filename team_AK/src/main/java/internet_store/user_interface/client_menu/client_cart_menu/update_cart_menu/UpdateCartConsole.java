package internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu;

import internet_store.core.request.cart.UpdateCartRequest;
import internet_store.core.response.cart.UpdateCartResponse;
import internet_store.core.service.cart.UpdateCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCartConsole {
    @Autowired
    UpdateCartService updateCartService;

    public void updateCart() {
        UpdateCartIdMenu updateCartIdMenu = new UpdateCartIdMenu();
        UpdateCartQuantityMenu updateCartQuantityMenu = new UpdateCartQuantityMenu();

        updateCartIdMenu.showMenuUpdateCart();
        long productId = updateCartIdMenu.getUserUpdatedCartIdInput();
        updateCartQuantityMenu.showMenuUpdateQuantityCart();
        long newQuantity = updateCartQuantityMenu.getUserUpdatedProductQuantityInput();

        UpdateCartRequest request = new UpdateCartRequest(productId, newQuantity);
        UpdateCartResponse response = updateCartService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Product updated in cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}