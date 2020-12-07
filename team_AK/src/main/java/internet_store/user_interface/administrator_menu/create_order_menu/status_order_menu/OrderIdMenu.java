package internet_store.user_interface.administrator_menu.create_order_menu.status_order_menu;

import dependency.annotation.DIComponent;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

@DIComponent
public class OrderIdMenu {
    @Getter
    private long userDeletedOrderIdInput;

    public void showMenuDeleteOrderId() {
        boolean errorInput;
        System.out.println("Status update for order");
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