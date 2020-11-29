package internet_store.user_interface.client_menu.ordering_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.service.ordering.OrderCreator;
import internet_store.core.service.ordering.OrderService;

public class ClientOrderConsole {


    public void startOrderMenu() {
        final ClientOrderMenu clientOrderMenu = ProductListApplication.applicationContext
                .getBean(ClientOrderMenu.class);
        final OrderService orderService = ProductListApplication.applicationContext
                .getBean(OrderService.class);
        final OrderCreator orderCreator = ProductListApplication.applicationContext
                .getBean(OrderCreator.class);

        clientOrderMenu.showMenuOrderClientId();
        long userInput = clientOrderMenu.getUserOrderClientIdInput();

        OrderRequest request = new OrderRequest(userInput);
        OrderResponse response = orderService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(r -> System.out.println(r.getField() + r.getMessage()));
        } else {
            orderCreator.createOrder(response.getId());
        }
    }
}