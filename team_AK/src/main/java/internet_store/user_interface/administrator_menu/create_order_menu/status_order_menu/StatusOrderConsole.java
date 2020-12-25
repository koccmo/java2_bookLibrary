package internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu;

import internet_store.core.request.ordering.CheckOrderIdRequest;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.response.ordering.CheckOrderIdResponse;
import internet_store.core.service.ordering.CheckOrderService;
import internet_store.core.service.ordering.OrderStatus;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StatusOrderConsole {
    @Autowired
    StatusOrderMenu statusOrderMenu;
    @Autowired
    OrderIdMenu orderIdMenu;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderMenuConsole orderMenuConsole;
    @Autowired
    CheckOrderService checkOrderService;

    public void startOrderStatusMenuConsole() {
        boolean returnOrderMenu = true;

        orderIdMenu.showMenuDeleteOrderId();
        long orderId = orderIdMenu.getUserDeletedOrderIdInput();
        if (!(isOrderIdValidate(orderId))) {
            return;
        }

        do {
            statusOrderMenu.showStatusOrderMenu();
            int userInput = statusOrderMenu.getUserInput();

            switch (userInput) {
                case 1 -> {
                    orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ORDER_RECEIVED, orderId));
                    returnOrderMenu = false;
                }
                case 2 -> {
                    orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ITEM_ORDERED_TO_STOCK, orderId));
                    returnOrderMenu = false;
                }
                case 3 -> {
                    orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ORDER_WAITING_OFFICE, orderId));
                    returnOrderMenu = false;
                }
                case 4 -> {
                    orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.CLIENT_INFO_BY_PHONE, orderId));
                    returnOrderMenu = false;
                }
                case 5 -> {
                    orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.DELIVERED, orderId));
                    returnOrderMenu = false;
                }
                case 6 -> returnOrderMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnOrderMenu);
        System.out.println("Status updated");
        orderMenuConsole.startOrderMenuConsole();
    }

    private boolean isOrderIdValidate(long orderId) {

        CheckOrderIdRequest request = new CheckOrderIdRequest(orderId);
        CheckOrderIdResponse response = checkOrderService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
            return false;
        }
        return true;
    }
}