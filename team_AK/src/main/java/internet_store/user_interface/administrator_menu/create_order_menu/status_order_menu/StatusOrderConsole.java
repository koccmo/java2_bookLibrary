package internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu;

import internet_store.ProductListApplication;
import internet_store.core.request.check_order_id.CheckOrderIdRequest;
import internet_store.core.request.order_status_update.OrderStatusRequest;
import internet_store.core.response.check_order_id.CheckOrderIdResponse;
import internet_store.ordering.OrderStatus;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;

public class StatusOrderConsole {
    private final StatusOrderMenu statusOrderMenu = new StatusOrderMenu();
    private final OrderIdMenu orderIdMenu = new OrderIdMenu();
    private final OrderMenuConsole orderMenuConsole;

    public StatusOrderConsole(OrderMenuConsole orderMenuConsole) {
        this.orderMenuConsole = orderMenuConsole;
    }

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
                    ProductListApplication.orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ORDER_RECEIVED, orderId));
                    returnOrderMenu = false;
                }
                case 2 -> {
                    ProductListApplication.orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ITEM_ORDERED_TO_STOCK, orderId));
                    returnOrderMenu = false;
                }
                case 3 -> {
                    ProductListApplication.orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.ORDER_WAITING_OFFICE, orderId));
                    returnOrderMenu = false;
                }
                case 4 -> {
                    ProductListApplication.orderStatusService.execute(
                            new OrderStatusRequest(OrderStatus.CLIENT_INFO_BY_PHONE, orderId));
                    returnOrderMenu = false;
                }
                case 5 -> {
                    ProductListApplication.orderStatusService.execute(
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
        CheckOrderIdResponse response = ProductListApplication.checkOrderService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(r -> System.out.println(r.getField() +
                    r.getMessage()));
            return false;
        }
        return true;
    }
}