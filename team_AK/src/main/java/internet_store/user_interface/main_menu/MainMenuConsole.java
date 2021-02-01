package internet_store.user_interface.main_menu;

import internet_store.core.service.SynchronizeDatabaseService;
import internet_store.core.service.cart.PrintCartService;
import internet_store.core.service.client.PrintClientService;
import internet_store.core.service.product.PrintProductService;
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenuConsole;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderConsole;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MainMenuConsole {
    @Autowired
    MainMenu mainMenu;
    @Autowired
    ClientMenuConsole clientMenuConsole;
    @Autowired
    ProductMenuConsole productMenuConsole;
    @Autowired
    OrderMenuConsole orderMenuConsole;
    @Autowired
    PrintClientService printClientService;
    @Autowired
    ClientCartConsole clientCartConsole;
    @Autowired
    PrintCartService printCartService;
    @Autowired
    PrintProductService printProductService;
    @Autowired
    ClientOrderConsole clientOrderConsole;
    @Autowired
    SynchronizeDatabaseService databaseService;

    public void startMainMenu() {
        do {
            mainMenu.showMainMenu();
            int userInput = mainMenu.getUserInput();
            switch (userInput) {
                case 1 -> clientMenuConsole.startClientMenuConsole();
                case 2 -> productMenuConsole.startProductMenuConsole();
                case 3 -> orderMenuConsole.startOrderMenuConsole();
                case 4 -> printClientService.print();
                case 5 -> clientCartConsole.startCartMenuConsole();
                case 6 -> printCartService.print();
                case 7 -> printProductService.print();
                case 8 -> clientOrderConsole.startOrderMenu();
                case 9 -> databaseService.synchronize();
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}