package internet_store.user_interface.main_menu;

import internet_store.core.service.cart.PrintCartService;
import internet_store.core.service.client.PrintClientService;
import internet_store.core.service.product.PrintProductService;
import internet_store.user_interface.administrator_menu.create_client_menu.ClientMenuConsole;
import internet_store.user_interface.administrator_menu.create_order_menu.OrderMenuConsole;
import internet_store.user_interface.administrator_menu.create_product_menu.ProductMenuConsole;
import internet_store.user_interface.client_menu.client_cart_menu.ClientCartConsole;
import internet_store.user_interface.client_menu.ordering_menu.ClientOrderConsole;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class MainMenuConsole {
    @DIDependency
    MainMenu mainMenu;
    @DIDependency
    ClientMenuConsole clientMenuConsole;
    @DIDependency
    ProductMenuConsole productMenuConsole;
    @DIDependency
    OrderMenuConsole orderMenuConsole;
    @DIDependency
    PrintClientService printClientService;
    @DIDependency
    ClientCartConsole clientCartConsole;
    @DIDependency
    PrintCartService printCartService;
    @DIDependency
    PrintProductService printProductService;
    @DIDependency
    ClientOrderConsole clientOrderConsole;

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
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}