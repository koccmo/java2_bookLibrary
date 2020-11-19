package lesson_3.user_interface.administrator_menu.create_client_menu.delete_client_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteClientMenu {
    @Getter
    private long userDeletedClientIdInput;

    public void showMenuDeleteProduct() {
        boolean errorInput;
        System.out.println("Delete information about client");
        System.out.println("Enter ID number");
        do {
            try {
                userDeletedClientIdInput = new Scanner(System.in).nextLong();
                errorInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again.");
                errorInput = true;
            }
        } while (errorInput);
    }
}