package internet_store.user_interface.client_menu.client_cart_menu;

import internet_store.ProductListApplication;
import internet_store.user_interface.client_menu.client_cart_menu.add_to_cart_menu.AddProductToCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu.DeleteFromCartConsole;
import internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu.UpdateCartConsole;
import internet_store.user_interface.main_menu.MainMenuConsole;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class ClientCartConsole {
    @DIDependency
    MainMenuConsole mainMenuConsole;
    @DIDependency
    ClientCartMenu clientCartMenu;
    @DIDependency
    AddProductToCartConsole addProductToCartConsole;
    @DIDependency
    DeleteFromCartConsole deleteFromCartConsole;
    @DIDependency
    UpdateCartConsole updateCartConsole;

//    public ClientCartConsole() {}
//
//    public ClientCartConsole(MainMenuConsole mainMenuConsole) {
//        this.mainMenuConsole = mainMenuConsole;
//    }

    public void startCartMenuConsole() {
//        final ClientCartMenu clientCartMenu = ProductListApplication.applicationContext
//                .getBean(ClientCartMenu.class);
//        final AddProductToCartConsole addProductToCartConsole = ProductListApplication.applicationContext
//                .getBean(AddProductToCartConsole.class);
//        final DeleteFromCartConsole deleteFromCartConsole = ProductListApplication.applicationContext
//                .getBean(DeleteFromCartConsole.class);
//        final UpdateCartConsole updateCartConsole = ProductListApplication.applicationContext
//                .getBean(UpdateCartConsole.class);

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