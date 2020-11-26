package internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.delete_product_from_cart.DeleteProductFromCartRequest;
import internet_store.core.response.delete_from_cart.DeleteProductFromCartResponse;

public class DeleteFromCartConsole {
    public void deleteFromCart() {
        DeleteFromCartMenu deleteFromCartMenu = new DeleteFromCartMenu();
        deleteFromCartMenu.showMenuDeleteProductFromCart();
        long productId = deleteFromCartMenu.getUserDeletedFromCartIdInput();

        DeleteProductFromCartRequest request = new DeleteProductFromCartRequest(productId);
        DeleteProductFromCartResponse response = ProductListApplication.deleteProductFromCartService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Product deleted from cart");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}