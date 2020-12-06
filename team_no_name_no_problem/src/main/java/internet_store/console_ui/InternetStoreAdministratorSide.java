package internet_store.console_ui;

import internet_store.console_ui.product.*;
import internet_store.core.services.product.*;
import internet_store.dependency_injection.ApplicationContext;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreAdministratorSide {

    private ApplicationContext applicationContext = new ApplicationContext();

    private Map<Integer, UIAction> menuNumberToAction;


    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public InternetStoreAdministratorSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddProductUIAction(applicationContext.getBean(AddProductService.class)));
        menuNumberToAction.put(2, new DeleteByIdUIAction(applicationContext.getBean(DeleteByIdService.class)));
        menuNumberToAction.put(3, new GetAllProductsUIAction(applicationContext.getBean(GetAllProductsService.class)));
        menuNumberToAction.put(4, new FindByIdUIAction(applicationContext.getBean(FindProductByIdService.class)));
        menuNumberToAction.put(5, new SearchProductUIAction(applicationContext.getBean(SearchProductService.class)));
        menuNumberToAction.put(6, new ChangeProductUIAction(applicationContext.getBean(ChangeProductService.class)));
        menuNumberToAction.put(7, new ExitUIAction());
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
                "7   Exit to the Main Menu");
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

