package internet_store.console_ui;

import internet_store.console_ui.product.*;
import internet_store.core.services.product.*;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreAdministratorSide {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    ProductDatabase productDatabase = new ProductDatabaseImpl();

    AddProductRequestValidator addProductRequestValidator = new AddProductRequestValidator();
    AddProductService addProductService = new AddProductService(productDatabase, addProductRequestValidator);
    DeleteProductRequestValidator deleteProductRequestValidator = new DeleteProductRequestValidator();
    DeleteByIdService deleteByIdService = new DeleteByIdService( productDatabase, deleteProductRequestValidator);
    GetAllProductsService getAllProductsService = new GetAllProductsService(productDatabase);
    FindByIdRequestValidator findByIdRequestValidator = new FindByIdRequestValidator();
    FindProductByIdService findProductByIdService = new FindProductByIdService(productDatabase, findByIdRequestValidator);
    SearchProductRequestValidator searchProductRequestValidator = new SearchProductRequestValidator();
    SearchProductService searchProductService = new SearchProductService(productDatabase, searchProductRequestValidator);
    ChangeProductValidator changeProductValidator = new ChangeProductValidator();
    ChangeProductService changeProductService = new ChangeProductService(productDatabase, changeProductValidator);

    public InternetStoreAdministratorSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddProductUIAction(addProductService));
        menuNumberToAction.put(2, new DeleteByIdUIAction(deleteByIdService));
        menuNumberToAction.put(3, new GetAllProductsUIAction(getAllProductsService));
        menuNumberToAction.put(4, new FindByIdUIAction(findProductByIdService));
        menuNumberToAction.put(5, new SearchProductUIAction(searchProductService));
        menuNumberToAction.put(6, new ChangeProductUIAction(changeProductService));
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
                "1   Add product\n" +
                "2   Delete account by id\n" +
                "3   Get list of products\n" +
                "4   Find product by ID\n"+ //будет вызвать Шоппинг Карт
                "5   Search product\n" +
                "6   Change product\n" +
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

