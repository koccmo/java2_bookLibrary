package internet_store.user_interface.client_menu.client_cart_menu;

import internet_store.ProductListApplication;
import internet_store.user_interface.main_menu.MainMenuConsole;

public class ClientCartConsole {
    private final ClientCartMenu clientCartMenu = new ClientCartMenu();
    private final MainMenuConsole mainMenuConsole;

    public ClientCartConsole(MainMenuConsole mainMenuConsole) {
        this.mainMenuConsole = mainMenuConsole;
    }

    public void startCartMenuConsole() {
        boolean returnMainMenu = true;
        do {
            clientCartMenu.showClientCartMenu();
            int userInput = clientCartMenu.getUserInput();
            switch (userInput) {
                case 1 -> ProductListApplication.addToCartConsole.addToCart();
                case 2 -> ProductListApplication.deleteFromCartConsole.deleteFromCart();
                case 3 -> ProductListApplication.updateCartConsole.updateCart();
                case 4 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}