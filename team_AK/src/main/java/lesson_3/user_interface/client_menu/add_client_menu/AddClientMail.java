package lesson_3.user_interface.client_menu.add_client_menu;

import lombok.Getter;

import java.util.Scanner;

public class AddClientMail {
    @Getter
    private String userClientMailInput;

    public void showMenuClientMail() {
        System.out.println("Enter client Email:");
        userClientMailInput = new Scanner(System.in).nextLine();
    }
}