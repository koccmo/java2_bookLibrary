package internet_store.user_interface.main_menu;

import internet_store.ProductListApplication;
import internet_store.core.service.cart.PrintCartService;
import internet_store.core.service.client.PrintClientService;
import internet_store.core.service.product.PrintProductService;
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenuConsole;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderConsole;

public class MainMenuConsole {


    public void startMainMenu() {
        final MainMenu mainMenu = ProductListApplication.applicationContext
                .getBean(MainMenu.class);
        final ClientMenuConsole clientMenuConsole = ProductListApplication.applicationContext
                .getBean(ClientMenuConsole.class);
        final ProductMenuConsole productMenuConsole = ProductListApplication.applicationContext
                .getBean(ProductMenuConsole.class);
        final OrderMenuConsole orderMenuConsole = ProductListApplication.applicationContext
                .getBean(OrderMenuConsole.class);
        final PrintClientService printClientService = ProductListApplication.applicationContext
                .getBean(PrintClientService.class);
        final ClientCartConsole clientCartConsole = ProductListApplication.applicationContext
                .getBean(ClientCartConsole.class);
        final PrintCartService printCartService = ProductListApplication.applicationContext
                .getBean(PrintCartService.class);
        final PrintProductService printProductService = ProductListApplication.applicationContext
                .getBean(PrintProductService.class);
        final ClientOrderConsole clientOrderConsole = ProductListApplication.applicationContext
                .getBean(ClientOrderConsole.class);

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
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}