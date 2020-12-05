package internet_store.storeApp;

import internet_store.core.database.Database;
import internet_store.core.database.DatabaseMemory;
import internet_store.core.services.*;
import internet_store.core.services.validators.*;
import internet_store.uiAction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;
    private final Database database = new DatabaseMemory();
    private final DeleteProductByNameValidator deleteProductByNameValidator = new DeleteProductByNameValidator();
    private final DeleteByProductIdValidator deleteProductIDValidator = new DeleteByProductIdValidator();
    private final DeleteByProductValidator deleteByProductValidator = new DeleteByProductValidator();
    private final FindByIDValidator findByIDValidator = new FindByIDValidator();
    private final AddProductValidator addProductValidator = new AddProductValidator();
    private final ChangeProductNameValidator changeProductNameValidator = new ChangeProductNameValidator();
    private final FindProductRequestValidator findProductRequestValidator = new FindProductRequestValidator();


    AddProductService addProductService = new AddProductService(database, addProductValidator);
    FindByIdService findByIdService = new FindByIdService(database, findByIDValidator);
    GetProductListService getProductListService = new GetProductListService(database);
    DeleteProductService deleteByNameService = new DeleteProductService(database, deleteProductByNameValidator);
    DeleteProductService deleteByProductService = new DeleteProductService(database, deleteProductByNameValidator);
    DeleteProductByIdService deleteProductByIdService = new DeleteProductByIdService(database, deleteProductIDValidator);
    ChangeProductNameService changeProductNameService = new ChangeProductNameService(database, changeProductNameValidator);
    OrderingProductService orderingProductService = new OrderingProductService();
    PagingProductService pagingProductService = new PagingProductService();
    FindProductService findProductService = new FindProductService(database, findProductRequestValidator,
            orderingProductService,
            pagingProductService);

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new AddProductUIAction(addProductService));
        menuNumberToActionMap.put(2, new ChangeProductNameUIAction(changeProductNameService, findByIdService));
        menuNumberToActionMap.put(3, new DeleteProductByIDUIAction(deleteProductByIdService));
        menuNumberToActionMap.put(4, new DeleteProductByNameUIAction(deleteByNameService));
        menuNumberToActionMap.put(5, new DeleteProductUIAction(deleteByProductService));
        menuNumberToActionMap.put(6, new FindProductByIDUIAction(findByIdService));
        menuNumberToActionMap.put(7, new FindProductUIAction(findProductService));
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
