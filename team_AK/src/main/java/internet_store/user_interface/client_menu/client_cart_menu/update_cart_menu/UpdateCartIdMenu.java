package internet_store.user_interface.client_menu.client_cart_menu.update_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCartIdMenu {
    @Getter
    private long userUpdatedCartIdInput;

    public void showMenuUpdateCart() {
        boolean errorInput;
        System.out.println("Update product quantity");
        System.out.println("Enter ID number");
        do {
            try {
                userUpdatedCartIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}