package internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu;

import internet_store.core.request.ordering.DeleteOrderRequest;
import internet_store.core.response.ordering.DeleteOrderResponse;
import internet_store.core.service.ordering.DeleteOrderService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DeleteOrderConsole {
    @Autowired
    DeleteOrderService deleteOrderService;

    public void deleteOrder() {
        DeleteOrderMenu deleteOrderMenu = new DeleteOrderMenu();
        deleteOrderMenu.showMenuDeleteOrder();
        long orderId = deleteOrderMenu.getUserDeletedOrderIdInput();

        DeleteOrderRequest request = new DeleteOrderRequest(orderId);
        DeleteOrderResponse response = deleteOrderService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Order deleted");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}