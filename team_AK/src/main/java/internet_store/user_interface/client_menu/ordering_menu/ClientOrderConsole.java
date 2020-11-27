package internet_store.user_interface.client_menu.ordering_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;

public class ClientOrderConsole {
    private final ClientOrderMenu orderMenu = new ClientOrderMenu();

    public void startOrderMenu() {
        orderMenu.showMenuOrderClientId();
        long userInput = orderMenu.getUserOrderClientIdInput();

        OrderRequest request = new OrderRequest(userInput);
        OrderResponse response = ProductListApplication.orderService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(r -> System.out.println(r.getField() + r.getMessage()));
        } else {
            ProductListApplication.orderCreator.createOrder(response.getId());
        }
    }
}