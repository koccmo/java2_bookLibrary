package internet_store.user_interface.client_menu.ordering_menu;

import internet_store.core.request.ordering.OrderRequest;
import internet_store.core.response.ordering.OrderResponse;
import internet_store.core.service.ordering.OrderCreator;
import internet_store.core.service.ordering.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ClientOrderConsole {
    @Autowired
    ClientOrderMenu clientOrderMenu;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderCreator orderCreator;


    public void startOrderMenu() {
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