package lesson_3.user_interface.client_menu.update_client_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateClientMenu {
    @Getter
    private long userUpdatedClientIdInput;

    public void showMenuUpdateProduct() {
        boolean errorInput;
        System.out.println("Update client data");
        System.out.println("Enter ID number");
        do {
            try {
                userUpdatedClientIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}
