package internet_store.console_ui;

import internet_store.console_ui.product.BuyProductUIAction;
import internet_store.core.services.product.BuyProductService;
import internet_store.dependency_injection.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BuyMenu implements UIAction {

    private ApplicationContext applicationContext = new ApplicationContext();

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public BuyMenu() {

        menuNumberToAction = new HashMap<>();

        menuNumberToAction.put(1, new BuyProductUIAction(applicationContext.getBean(BuyProductService.class)));

    }

    @Override
    public void execute() {

        while (true) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu() {
        System.out.println("\nMenu\n" +
                "1   Buy product\n" +
                "2   Place an order");
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
