package internet_store.console_ui;

import internet_store.console_ui.product.*;
import internet_store.core.services.product.*;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;


import java.util.HashMap;
import java.util.Map;

public class InternetStore {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    ProductDatabase productDatabase = new ProductDatabaseImpl();
    AddProductValidator addProductValidator = new AddProductValidator();
    AddProductService addProductService = new AddProductService(productDatabase, addProductValidator);
    DeleteProductValidator deleteProductValidator = new DeleteProductValidator();
    DeleteByIdService deleteByIdService = new DeleteByIdService( productDatabase, deleteProductValidator);
    GetAllProductsService getAllProductsService = new GetAllProductsService(productDatabase);
    ChangeTitleService changeTitleService = new ChangeTitleService(productDatabase);
    ChangeDescriptionService changeDescriptionService = new ChangeDescriptionService(productDatabase);
    FindAnyByTitleService findAnyByTitleService = new FindAnyByTitleService(productDatabase);
    FindAllByTitleService findAllByTitleService = new FindAllByTitleService(productDatabase);
    FindByIdValidator findByIdValidator = new FindByIdValidator();
    FindProductByIdService findProductByIdService = new FindProductByIdService(productDatabase, findByIdValidator);

    public InternetStore() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddProductUIAction(addProductService));
        menuNumberToAction.put(2, new DeleteByIdUIAction(deleteByIdService));
        menuNumberToAction.put(3, new GetAllProductsUIAction(getAllProductsService));
        menuNumberToAction.put(4, new ChangeTitleUIAction(changeTitleService));
        menuNumberToAction.put(5, new ChangeDescriptionUIAction(changeDescriptionService));
        menuNumberToAction.put(6, new FindAnyByTitleUIAction(findAnyByTitleService));
        menuNumberToAction.put(7, new FindAllByTitleUIAction(findAllByTitleService));
        menuNumberToAction.put(8, new FindByIdUIAction(findProductByIdService));
        menuNumberToAction.put(0, new ExitUIAction());
    }

    public void run() {

        while (true) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Add item\n" +
                "2   Delete by id\n" +
                "3   Print products\n" +
                "4   Change title\n" +
                "5   Change description\n" +
                "6   Find any by title\n" +
                "7   Find all by title\n" +
                "8   Find product by id\n"+
                "0   Exit");
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}

