package internet_store.user_interface.client_menu.client_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientCartMenu {
    @Getter
    private int userInput;

    public void showClientCartMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("1 - Add product to cart");
        System.out.println("2 - Delete product from cart");
        System.out.println("3 - Update product in cart");
        System.out.println("4 - Return");

        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}
