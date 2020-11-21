package internet_store.storeApp;

import internet_store.core.database.Database;
import internet_store.core.database.DatabaseMemory;
import internet_store.core.services.*;
import internet_store.uiAction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;
    private final Database database = new DatabaseMemory();
    private final DeleteProductByNameValidator productNameValidator = new DeleteProductByNameValidator();
    private final DeleteByProductIdValidator productIDValidator = new DeleteByProductIdValidator();


    AddProductService addProductService = new AddProductService(database);
    FindProductService findProductService = new FindProductService(database);
    GetProductListService getProductListService = new GetProductListService(database);
    DeleteProductService deleteProductService = new DeleteProductService(database, productNameValidator);
    DeleteProductByIdService deleteProductByIdService = new DeleteProductByIdService(database, productIDValidator);
    ChangeProductNameService changeProductNameService = new ChangeProductNameService(database);

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new AddProductUIAction(addProductService));
        menuNumberToActionMap.put(2, new ChangeProductNameUIAction(changeProductNameService));
        menuNumberToActionMap.put(3, new DeleteProductByIDUIAction(deleteProductByIdService));
        menuNumberToActionMap.put(4, new DeleteProductByNameUIAction(deleteProductService));
        menuNumberToActionMap.put(5, new DeleteProductUIAction(deleteProductService));
        menuNumberToActionMap.put(6, new FindProductByIDUIAction(findProductService));
        menuNumberToActionMap.put(7, new FindProductByNameUIAction(findProductService));
        menuNumberToActionMap.put(8, new PrintProductToConsoleUIActon(getProductListService));
        menuNumberToActionMap.put(0, new ExitProgramUIAction());
    }

    private void printOutMenu() {
        System.out.println("Welcome to our store!");
        System.out.println("------------------------------------------------------");
        System.out.println("Please enter menu number!");
        System.out.println("1. Add product to database.");
        System.out.println("2. Change product name!");
        System.out.println("3. Delete product by ID.");
        System.out.println("4. Delete product by name.");
        System.out.println("5. Delete product by name, description and price.");
        System.out.println("6. Find product(s) by ID.");
        System.out.println("7. Find product(s) by name.");
        System.out.println("8. Print all product in database.");
        System.out.println("0. Exit the program.");
        System.out.println("......................................................");

    }

    private void executeUIAction(int userSelectedNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu number doesn't exist! '" + userSelectedNumber + "'");
        }
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                int userSelectedNumber = Integer.parseInt(scanner.nextLine());
                executeUIAction(userSelectedNumber);
            } catch (NumberFormatException exception) {
                System.out.println("Incorrect number! Please try again!");
            }
        }
    }
}
