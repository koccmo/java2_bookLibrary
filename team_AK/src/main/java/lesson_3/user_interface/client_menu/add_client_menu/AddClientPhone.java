package lesson_3.user_interface.client_menu.add_client_menu;

import lombok.Getter;

import java.util.Scanner;

public class AddClientPhone {
    @Getter
    private String userClientPhoneInput;

    public void showMenuClientPhone() {
        System.out.println("Enter client Phone:");
        userClientPhoneInput = new Scanner(System.in).nextLine();
    }
}