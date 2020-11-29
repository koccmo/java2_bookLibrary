package internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.ordering.DeleteOrderRequest;
import internet_store.core.response.ordering.DeleteOrderResponse;
import internet_store.core.service.ordering.DeleteOrderService;

public class DeleteOrderConsole {
    public void deleteOrder() {
        DeleteOrderMenu deleteOrderMenu = new DeleteOrderMenu();
        deleteOrderMenu.showMenuDeleteOrder();
        long orderId = deleteOrderMenu.getUserDeletedOrderIdInput();

        DeleteOrderRequest request = new DeleteOrderRequest(orderId);
        DeleteOrderService deleteOrderService = ProductListApplication.applicationContext
                .getBean(DeleteOrderService.class);
        DeleteOrderResponse response = deleteOrderService.execute(request);

        if (!(response.hasErrors())) {
            System.out.println("Order deleted");
        } else {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
        }
    }
}