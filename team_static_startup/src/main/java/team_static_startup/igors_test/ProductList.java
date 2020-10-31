package team_static_startup.igors_test;

import team_static_startup.igors_test.uiaction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;

    public ProductList() {
        ProductDatabase productDatabase = new ProductDatabaseImpl();

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new SaveProductUIAction(productDatabase));
        menuNumberToActionMap.put(2, new DeleteByIdUIAction(productDatabase));
//        menuNumberToActionMap.put(3, new DeleteProductUIAction(bookDatabase));
//        menuNumberToActionMap.put(4, new DeleteByAuthorUIAction(bookDatabase));
//        menuNumberToActionMap.put(5, new DeleteByTitleUIAction(bookDatabase));
//        menuNumberToActionMap.put(6, new FindByIdUIAction(bookDatabase));
//        menuNumberToActionMap.put(7, new FindByAuthorUIAction(bookDatabase));
//        menuNumberToActionMap.put(8, new FindByTitleUIAction(bookDatabase));
//        menuNumberToActionMap.put(9, new FindUniqueAuthorsUIAction(bookDatabase));
//        menuNumberToActionMap.put(10, new FindUniqueTitlesUIAction(bookDatabase));
//        menuNumberToActionMap.put(11, new FindUniqueBooksUIAction(bookDatabase));
//        menuNumberToActionMap.put(12, new CountAllBooksUIAction(bookDatabase));


    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Adding product to Database");
            System.out.println("2. Deleting product from Database by ID");
            System.out.println("3. Deleting product from Database by ***");
            System.out.println("0. Exit the program");
            System.out.println("---------------------------------------");
            System.out.print("Please enter menu number: ");
            int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
            if (userSelectedMenuNumber == 0) {
                System.out.println("Thank you! Good by!");
                break;
            } else {
                executeUIAction(userSelectedMenuNumber);
            }
        }
    }

    private void executeUIAction(int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item not exist: " + userSelectedMenuNumber);
        }
    }

}
