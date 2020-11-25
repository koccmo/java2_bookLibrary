package internet_store.user_interface.client_menu.client_cart_menu.delete_from_cart_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteFromCartMenu {
    @Getter
    private long userDeletedFromCartIdInput;

    public void showMenuDeleteProductFromCart() {
        boolean errorInput;
        System.out.println("Delete product from cart");
        System.out.println("Enter ID number");
        do {
            try {
                userDeletedFromCartIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}