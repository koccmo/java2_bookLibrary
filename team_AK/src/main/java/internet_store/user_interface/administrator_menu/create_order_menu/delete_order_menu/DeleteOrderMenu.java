package internet_store.user_interface.administrator_menu.create_order_menu.delete_order_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteOrderMenu {
    @Getter
    private long userDeletedOrderIdInput;

    public void showMenuDeleteOrder() {
        boolean errorInput;
        System.out.println("Delete order");
        System.out.println("Enter ID number");
        do {
            try {
                userDeletedOrderIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}