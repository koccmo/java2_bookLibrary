package internet_store.user_interface.administrator_menu.create_order_menu;

import internet_store.ProductListApplication;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class OrderMenuConsole {
    private final OrderMenu orderMenu = new OrderMenu();
    private final MainMenuConsole mainMenuConsole;

    public OrderMenuConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startOrderMenuConsole() {
        boolean returnMainMenu = true;
        do {
            orderMenu.showOrderMenu();
            int userInput = orderMenu.getUserInput();
            switch (userInput) {
                case 1 -> ProductListApplication.deleteOrderConsole.deleteOrder();
                case 2 -> ProductListApplication.statusOrderConsole.startOrderStatusMenuConsole();
                case 3 -> ProductListApplication.printOrderService.print();
                case 4 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}