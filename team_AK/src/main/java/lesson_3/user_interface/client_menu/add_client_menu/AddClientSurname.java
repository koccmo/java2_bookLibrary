package lesson_3.user_interface.client_menu.add_client_menu;

import lombok.Getter;

import java.util.Scanner;

public class AddClientSurname {
    @Getter
    private String userClientSurnameInput;

    public void showMenuClientSurname() {
        System.out.println("Enter client Surname:");
        userClientSurnameInput = new Scanner(System.in).nextLine();
    }
}