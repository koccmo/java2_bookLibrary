package internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import internet_store.core.service.cart.DeleteProductFromCartService;

public class DeleteFromCartConsole {

    public void deleteFromCart() {
        final DeleteProductFromCartService deleteProductFromCartService = ProductListApplication
                .applicationContext.getBean(DeleteProductFromCartService.class);

        DeleteFromCartMenu deleteFromCartMenu = new DeleteFromCartMenu();
        deleteFromCartMenu.showMenuDeleteProductFromCart();
        long productId = deleteFromCartMenu.getUserDeletedFromCartIdInput();

        DeleteProductFromCartRequest request = new DeleteProductFromCartRequest(productId);
        DeleteProductFromCartResponse response = deleteProductFromCartService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Product deleted from cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}