package internet_store_1.application;

import internet_store_1.application.database.*;
import internet_store_1.application.core.services.*;
import internet_store_1.application.console_ui.*;
import java.util.*;

class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;
    private final Database database = new InMemoryDatabase();
    private final DeleteByProductNameValidator validator = new DeleteByProductNameValidator();
    private final DeleteByProductIdValidator productIdValidator = new DeleteByProductIdValidator();
    private final FindProductValidator validatorFindProduct = new FindProductValidator();
    private final FindByIdValidator findByIdValidator = new FindByIdValidator();
    private final AddProductValidator addProductValidator = new AddProductValidator();


    AddProductService addProductService = new AddProductService(database, addProductValidator);
    FindProductService findProductService = new FindProductService(database, validatorFindProduct);
    FindByIdService findByIdService = new FindByIdService(database, findByIdValidator);
    GetProductListService getProductListService = new GetProductListService(database);
    DeleteProductService deleteProductService = new DeleteProductService(database, validator);
    DeleteByProductIdService deleteByProductIdService = new DeleteByProductIdService(database, productIdValidator);
    ChangeProductNameService changeProductNameService = new ChangeProductNameService(database);

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new AddProductUIAction(addProductService));
        menuNumberToActionMap.put(2, new DeleteByIdUIAction(deleteByProductIdService));
        menuNumberToActionMap.put(3, new DeleteByProductUIAction(deleteProductService));
        menuNumberToActionMap.put(4, new DeleteByProductNameUIAction(deleteProductService));
        menuNumberToActionMap.put(5, new PrintProductsToConsoleUIAction(getProductListService));
        menuNumberToActionMap.put(6, new FindByProductNameUIAction(findProductService));
        menuNumberToActionMap.put(7, new FindByIdUIAction(findByIdService));
        menuNumberToActionMap.put(8, new ChangeProductNameUIAction(changeProductNameService));
        menuNumberToActionMap.put(0, new ExitProgramUIAction());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
                executeUIAction(userSelectedMenuNumber);
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter number");
            }
        }
    }

    private void printOutMenu() {
        System.out.println("\n1. Add product to database");
        System.out.println("2. Delete product from database by ID");
        System.out.println("3. Delete product from database by name and description");
        System.out.println("4. Delete product from database by name");
        System.out.println("5. Print out all database products");
        System.out.println("6. Find product(s) from database by name");
        System.out.println("7. Find product(s) from database by ID");
        System.out.println("8. Find product(s) from database by ID and change name");
        System.out.println("0. Exit the program");
        System.out.println("---------------------------------------------------------");
        System.out.print("Please enter menu number: ");
    }

    private void executeUIAction(int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item doesn't exist: " + userSelectedMenuNumber);
        }
    }

}
