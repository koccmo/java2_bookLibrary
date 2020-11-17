package lesson_3.user_interface.product_menu.add_product_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddProductQuantity {
    @Getter
    private int userProductQuantityInput = 0;

    public void showMenuProductQuantity() {
        boolean errorInput;
        System.out.println("Enter product Quantity:");
        do {
            try {
                userProductQuantityInput = new Scanner(System.in).nextInt();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                System.out.println("Enter product Quantity:");
                errorInput = true;
            }
        } while (errorInput);
    }
}
