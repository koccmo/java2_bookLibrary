package lesson_3.user_interface.main_menu;

import lesson_3.ProductListApplication;

public class MainMenuConsole {
    private final MainMenu mainMenu = new MainMenu();

public void startMainMenu() {
        do {
            mainMenu.showMainMenu();
            int userInput = mainMenu.getUserInput();
            switch (userInput) {
                case 1 -> ProductListApplication.clientMenuConsole.startClientMenuConsole();
                case 2 -> ProductListApplication.productMenuConsole.startProductMenuConsole();
                case 3 -> ProductListApplication.addToCartConsole.addToCart();
                case 6 -> ProductListApplication.printProductService.print();
                case 8 -> System.exit(0);
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (true);
    }
}