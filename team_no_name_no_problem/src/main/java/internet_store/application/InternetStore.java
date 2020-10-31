package internet_store.application;

import internet_store.ProductDatabase;
import internet_store.ProductDatabaseImpl;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InternetStore {

    private Map<Integer, UIAction> menuNumberToAction;

    public InternetStore() {
        ProductDatabase productDatabase = new ProductDatabaseImpl();

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddItemUIAction(productDatabase));
    }

    public void run() {

        while (true) {
            System.out.println("\nMenu\n" +
                    "1   Add item\n+" +
                    "0   Exit");

            Scanner in = new Scanner(System.in);
            int userSelectedMenuNumber;

            while (true) {
                try {
                    System.out.println("Please enter menu number: ");
                    userSelectedMenuNumber = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number!");
                }
            }

            if (userSelectedMenuNumber == 0) {
                System.out.println(":) End of work day!");
                break;
            } else {
                executeUIAction(userSelectedMenuNumber);
            }
        }
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item not exist: " + userSelectedMenuNumber);
        }
    }

}

