package lesson_3.user_interface.client_menu.add_to_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SetQuantityMenu {
    @Getter
    private int userQuantityInput;

    public void showMenuSetQuantityProduct() {
        boolean errorInput = true;
        System.out.println("Set product quantity");
        System.out.println("Enter quantity");
        do {
            try {
                userQuantityInput = new Scanner(System.in).nextInt();
                if (isUserInputNegative(userQuantityInput)) {
                    continue;
                }
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }

    private boolean isUserInputNegative(int userQuantityInput) {
        return userQuantityInput < 0;
    }
}