package internet_store;

import internet_store.config.MainMenuConfiguration;
import internet_store.console_ui.ExitFromApplicationUIAction;
import internet_store.console_ui.UIAction;
import internet_store.console_ui.customer.AddCustomerUIAction;
import internet_store.console_ui.customer.DeleteCustomerUIAction;
import internet_store.console_ui.order.GetOrdersUIAction;
import internet_store.console_ui.product.*;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenuApplication {

    private static ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    public static void main(String[] args) {

        System.out.println("\nWelcome to the No Name No Problem Internet Store  ¯\\_(ツ)_/¯");

        while (true) {

            printMenu();

            int userSelectedMenuNumber = inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }

    }

    private static void printMenu () {

        System.out.println("\nAdministrator Menu\n\n" +
                "1   Add product\n" +
                "2   Delete account by id\n" +
                "3   Get list of products\n" +
                "4   Find product by ID\n"+
                "5   Search product\n" +
                "6   Change product\n" +
                "7   Get order list");

        System.out.println("\nCustomer Menu\n\n" +
                "8   Sign in\n" +
                "9   Delete account by id\n" +
                "10  Search product\n" +
                "11  See product list\n" +
                "12  Buy product\n"+

                "0   Exit\n");
    }

    private static void executeUIAction (int userSelectedMenuNumber) {
        Map<Integer, UIAction> menuNumberToAction;
        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, appContext.getBean(AddProductUIAction.class));
        menuNumberToAction.put(2, appContext.getBean(DeleteByIdUIAction.class));
        menuNumberToAction.put(3, appContext.getBean(GetAllProductsUIAction.class));
        menuNumberToAction.put(4, appContext.getBean(FindByIdUIAction.class));
        menuNumberToAction.put(5, appContext.getBean(SearchProductUIAction.class));
        menuNumberToAction.put(6, appContext.getBean(ChangeProductUIAction.class));
        menuNumberToAction.put(7, appContext.getBean(GetOrdersUIAction.class));

        menuNumberToAction.put(8, appContext.getBean(AddCustomerUIAction.class));
        menuNumberToAction.put(9, appContext.getBean(DeleteCustomerUIAction.class));
        menuNumberToAction.put(10, appContext.getBean(SearchProductUIAction.class));
        menuNumberToAction.put(11, appContext.getBean(GetAllProductsUIAction.class));
        menuNumberToAction.put(12, appContext.getBean(BuyProductUIAction.class));

        menuNumberToAction.put(0, new ExitFromApplicationUIAction());

        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

    private static int inputValidInteger(String message) {
        int input;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            while (true) {
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }

            if (input >= 0) {
                break;
            } else {
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }
}
