package internet_store.user_interface.client_menu.client_cart_menu;

import internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu.AddProductToCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu.DeleteFromCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu.UpdateCartConsole;
import internet_store.user_interface.main_menu.MainMenuConsole;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ClientCartConsole {
    @Autowired
    MainMenuConsole mainMenuConsole;
    @Autowired
    ClientCartMenu clientCartMenu;
    @Autowired
    AddProductToCartConsole addProductToCartConsole;
    @Autowired
    DeleteFromCartConsole deleteFromCartConsole;
    @Autowired
    UpdateCartConsole updateCartConsole;

    public void startCartMenuConsole() {
        boolean returnMainMenu = true;
        do {
            clientCartMenu.showClientCartMenu();
            int userInput = clientCartMenu.getUserInput();
            switch (userInput) {
                case 1 -> addProductToCartConsole.addToCart();
                case 2 -> deleteFromCartConsole.deleteFromCart();
                case 3 -> updateCartConsole.updateCart();
                case 4 -> returnMainMenu = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        } while (returnMainMenu);
        mainMenuConsole.startMainMenu();
    }
}