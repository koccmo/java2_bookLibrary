package internet_store.console_ui;

import internet_store.console_ui.customer.*;
import internet_store.console_ui.product.ExitUIAction;
import internet_store.console_ui.product.GetAllProductsUIAction;
import internet_store.console_ui.product.SearchProductUIAction;
import internet_store.core.services.customer.*;
import internet_store.core.services.product.GetAllProductsService;
import internet_store.core.services.product.SearchProductService;
import internet_store.core.services.shopping_cart.AddToShoppingCartService;
import internet_store.dependency_injection.ApplicationContext;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreCustomerSide {

    private ApplicationContext applicationContext = new ApplicationContext();

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public InternetStoreCustomerSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddCustomerUIAction(applicationContext.getBean(AddCustomerService.class)));
        menuNumberToAction.put(2, new DeleteCustomerUIAction(applicationContext.getBean(DeleteCustomerService.class)));
        menuNumberToAction.put(3, new SearchProductUIAction(applicationContext.getBean(SearchProductService.class)));
        menuNumberToAction.put(4, new GetAllProductsUIAction(applicationContext.getBean(GetAllProductsService.class)));
        menuNumberToAction.put(5, new AddProductToShoppingCartUIAction(applicationContext.getBean(AddToShoppingCartService.class)));
        menuNumberToAction.put(6, new ExitUIAction());
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
                "1   Sign in\n" +
                "2   Delete account by id\n" +
                "3   Search product\n" +
                "4   See product list\n" +
                "5   Buy product\n"+ //будет вызвать Шоппинг Карт
                "6   Exit to the Main Menu");
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

