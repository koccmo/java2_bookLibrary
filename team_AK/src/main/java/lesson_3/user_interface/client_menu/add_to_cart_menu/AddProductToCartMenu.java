package lesson_3.user_interface.client_menu.add_to_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddProductToCartMenu {
    @Getter
    private long userProductIdInput;

    public void showMenuAddToCarProduct() {
        boolean errorInput;
        System.out.println("Product ID to add cart");
        System.out.println("Enter ID number");
        do {
            try {
                userProductIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}
