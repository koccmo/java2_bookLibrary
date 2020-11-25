package internet_store_1;

import internet_store_1.console_ui.InternetStoreBusinessSide;
import internet_store_1.console_ui.InternetStoreCustomerSide;

import java.util.Scanner;

public class MainMenuApplication {

    public static void main(String[] args) {

        InternetStoreBusinessSide internetStoreBusinessSide = new InternetStoreBusinessSide();
        InternetStoreCustomerSide internetStoreCustomerSide = new InternetStoreCustomerSide();

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");
        System.out.println("In order to proceed further, please select your path from the list below: ");
        System.out.println();

        System.out.println("1 - Administrator of the store");
        System.out.println("2 - Customer of the store");
        System.out.println();

        System.out.println("Please enter you choice here: ");

        int choice = in.nextInt();

        switch (choice) {

            case 1: internetStoreBusinessSide.run();
            break;

            case 2: internetStoreCustomerSide.run();
            break;

            default:
                System.out.println("There is no such option");
        }
    }
}
