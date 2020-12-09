package internet_store;

import internet_store.console_ui.InternetStoreAdministratorSide;
import internet_store.console_ui.InternetStoreCustomerSide;
import internet_store.dependency_injection.ApplicationContext;
import internet_store.dependency_injection.DIApplicationContextBuilder;

import java.util.Scanner;

public class MainMenuApplication {

    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("internet_store");

    public static void main(String[] args) {

        InternetStoreAdministratorSide internetStoreAdministratorSide = new InternetStoreAdministratorSide();
        InternetStoreCustomerSide internetStoreCustomerSide = new InternetStoreCustomerSide();

        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");
        System.out.println("\nIn order to proceed further, please select your path from the list below: ");
        System.out.println();

        System.out.println("1 - Administrator path");
        System.out.println("2 - Customer path");
        System.out.println();

        System.out.println("Please enter you choice here: ");
        int choice = in.nextInt();

        switch (choice) {

            case 1: internetStoreAdministratorSide.run();
            break;

            case 2: internetStoreCustomerSide.run();
            break;

            default:
                System.out.println("There is no such option. Please, choose number 1 or 2");
        }
    }

    public void execute() {
        rerunMainMenu();
    }

    private void rerunMainMenu() {
        InternetStoreAdministratorSide internetStoreAdministratorSide = new InternetStoreAdministratorSide();
        InternetStoreCustomerSide internetStoreCustomerSide = new InternetStoreCustomerSide();

        Scanner in = new Scanner(System.in);

        System.out.println("1 - Administrator path");
        System.out.println("2 - Customer path");
        System.out.println();

        System.out.println("Please enter you choice here: ");
        int choice = in.nextInt();

        switch (choice) {

            case 1: internetStoreAdministratorSide.run();
                break;

            case 2: internetStoreCustomerSide.run();
                break;

            default:
                System.out.println("There is no such option. Please, choose number 1 or 2");
        }
    }
}
