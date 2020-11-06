package internet_store.UI;

import internet_store.UI.product.*;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;
import internet_store.services.product.AddProductService;
import internet_store.services.product.ChangeTitleService;
import internet_store.services.product.DeleteByIdService;
import internet_store.services.product.GetAllProductsService;

import java.util.HashMap;
import java.util.Map;

public class InternetStore {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    ProductDatabase productDatabase = new ProductDatabaseImpl();
    AddProductService addProductService = new AddProductService(productDatabase);
    DeleteByIdService deleteByIdService = new DeleteByIdService(productDatabase);
    GetAllProductsService getAllProductsService = new GetAllProductsService(productDatabase);
    ChangeTitleService changeTitleService = new ChangeTitleService(productDatabase);

    public InternetStore() {


        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddProductUIAction(addProductService));
        menuNumberToAction.put(2, new DeleteByIdUIAction(deleteByIdService));
        menuNumberToAction.put(3, new GetAllProductsUIAction(getAllProductsService));
        menuNumberToAction.put(4, new ChangeTitleUIAction(changeTitleService));
        menuNumberToAction.put(5, new ChangeDescriptionUIAction(productDatabase));
        menuNumberToAction.put(6, new FindAnyByTitleUIAction(productDatabase));
        menuNumberToAction.put(7, new FindAllByTitleUIAction(productDatabase));
    }

    public void run() {

        boolean isWorking = true;

        while (isWorking) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            isWorking = isNullNotPressed(userSelectedMenuNumber);
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
                "0   Exit");
    }

    private boolean isNullNotPressed(int userSelectedMenuNumber){
        if (userSelectedMenuNumber == 0) {
            System.out.println(":) End of work day!");
            return false;
        } else {
            executeUIAction(userSelectedMenuNumber);
            return true;
        }
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

