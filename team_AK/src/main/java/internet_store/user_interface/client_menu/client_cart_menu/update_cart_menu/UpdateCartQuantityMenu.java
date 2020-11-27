package internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCartQuantityMenu {
    @Getter
    private int userUpdatedProductQuantityInput;

    public void showMenuUpdateQuantityCart() {
        boolean errorInput;
        System.out.println("Set new product quantity");
        System.out.println("Enter quantity");
        do {
            try {
                userUpdatedProductQuantityInput = new Scanner(System.in).nextInt();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}