package lesson_3.user_interface.administrator_menu.create_client_menu.add_client_menu;

import lombok.Getter;

import java.util.Scanner;

public class AddClientName {
    @Getter
    private String userClientNameInput;

    public void showMenuClientName() {
        System.out.println("Enter client Name:");
        userClientNameInput = new Scanner(System.in).nextLine();
    }
}