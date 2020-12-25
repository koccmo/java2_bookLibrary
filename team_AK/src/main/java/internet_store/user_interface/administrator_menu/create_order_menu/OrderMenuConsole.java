package internet_store.user_interface.administrator_menu.create_order_menu;

import internet_store.core.service.ordering.PrintOrderService;
import internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu.DeleteOrderConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu.StatusOrderConsole;
import internet_store.user_interface.main_menu.MainMenuConsole;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class OrderMenuConsole {
    @Autowired
    MainMenuConsole mainMenuConsole;
    @Autowired
    OrderMenu orderMenu;
    @Autowired
    DeleteOrderConsole deleteOrderConsole;
    @Autowired
    StatusOrderConsole statusOrderConsole;
    @Autowired
    PrintOrderService printOrderService;

    public void startOrderMenuConsole() {
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