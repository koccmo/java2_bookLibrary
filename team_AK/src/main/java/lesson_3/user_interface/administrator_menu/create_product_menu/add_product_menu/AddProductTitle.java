package lesson_3.user_interface.administrator_menu.create_product_menu.add_product_menu;

import lombok.Getter;

import java.util.Scanner;

public class AddProductTitle {
    @Getter
    private String userProductTitleInput;

    public void showMenuProductTitle() {
        System.out.println("Enter product Title:");
        userProductTitleInput = new Scanner(System.in).nextLine();
    }
}