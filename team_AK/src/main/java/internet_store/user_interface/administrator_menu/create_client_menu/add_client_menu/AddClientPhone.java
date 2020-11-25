package internet_store.user_interface.administrator_menu.create_client_menu.add_client_menu;

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