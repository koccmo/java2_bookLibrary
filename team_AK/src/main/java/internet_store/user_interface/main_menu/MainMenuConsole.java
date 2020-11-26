package internet_store.user_interface.main_menu;

import internet_store.ProductListApplication;

public class MainMenuConsole {
    private final MainMenu mainMenu = new MainMenu();

    public void startMainMenu() {
        do {
            mainMenu.showMainMenu();
            int userInput = mainMenu.getUserInput();
            switch (userInput) {
                case 1 -> ProductListApplication.clientMenuConsole.startClientMenuConsole();
                case 2 -> ProductListApplication.productMenuConsole.startProductMenuConsole();
                case 3 -> ProductListApplication.orderMenuConsole.startOrderMenuConsole();
                case 4 -> ProductListApplication.printClientService.print();
                case 5 -> ProductListApplication.clientCartConsole.startCartMenuConsole();
                case 6 -> ProductListApplication.printCartService.print();
                case 7 -> ProductListApplication.printProductService.print();
                case 8 -> ProductListApplication.clientOrderConsole.startOrderMenu();
                case 0 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}