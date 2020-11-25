package internet_store.user_interface.administrator_menu.create_client_menu;

import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientMenu {
    @Getter
    private int userInput;

    public void showClientMenu() {
        final int INCORRECT_USER_INPUT = -1;
        System.out.println("1 - Add client to list");
        System.out.println("2 - Delete client from list");
        System.out.println("3 - Update client from list");
        System.out.println("4 - View client's list");
        System.out.println("5 - Return");
        try {
            userInput = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            userInput = INCORRECT_USER_INPUT;
        }
    }
}
