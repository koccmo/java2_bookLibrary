package internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.cart.UpdateCartRequest;
import internet_store.core.response.cart.UpdateCartResponse;
import internet_store.core.service.cart.UpdateCartService;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.math.BigDecimal;

@DIComponent
public class UpdateCartConsole {
    @DIDependency
    UpdateCartService updateCartService;

    public void updateCart() {
//        final UpdateCartService updateCartService = ProductListApplication.applicationContext
//                .getBean(UpdateCartService.class);
        UpdateCartIdMenu updateCartIdMenu = new UpdateCartIdMenu();
        UpdateCartQuantityMenu updateCartQuantityMenu = new UpdateCartQuantityMenu();

        updateCartIdMenu.showMenuUpdateCart();
        long productId = updateCartIdMenu.getUserUpdatedCartIdInput();
        updateCartQuantityMenu.showMenuUpdateQuantityCart();
        BigDecimal newQuantity = new BigDecimal(String.valueOf(updateCartQuantityMenu.getUserUpdatedProductQuantityInput()));

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