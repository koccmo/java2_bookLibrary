package internet_store.user_interface.administrator_menu.create_order_menu;

import internet_store.ProductListApplication;
import internet_store.core.service.ordering.PrintOrderService;
import internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu.DeleteOrderConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.StatusOrderConsole;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class OrderMenuConsole {
    private final MainMenuConsole mainMenuConsole;

    public OrderMenuConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startOrderMenuConsole() {
        final OrderMenu orderMenu = ProductListApplication.applicationContext
                .getBean(OrderMenu.class);
        final DeleteOrderConsole deleteOrderConsole = ProductListApplication.applicationContext
                .getBean(DeleteOrderConsole.class);
        final StatusOrderConsole statusOrderConsole = ProductListApplication.applicationContext
                .getBean(StatusOrderConsole.class);
        final PrintOrderService printOrderService = ProductListApplication.applicationContext
                .getBean(PrintOrderService.class);
        boolean returnMainMenu = true;
        do {
            orderMenu.showOrderMenu();
            int userInput = orderMenu.getUserInput();
            switch (userInput) {
                case 1 -> deleteOrderConsole.deleteOrder();
                case 2 -> statusOrderConsole.startOrderStatusMenuConsole();
                case 3 -> printOrderService.print();
                case 4 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}