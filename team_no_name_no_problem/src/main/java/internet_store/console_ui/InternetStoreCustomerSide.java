package internet_store.console_ui;

import internet_store.console_ui.customer.*;
import internet_store.console_ui.product.ExitUIAction;
import internet_store.core.services.customer.*;
import internet_store.database.customer.CustomerDatabase;
import internet_store.database.customer.CustomerDatabaseImpl;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreCustomerSide {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    CustomerDatabase customerDatabase = new CustomerDatabaseImpl();

    AddCustomerRequestValidator addCustomerRequestValidator = new AddCustomerRequestValidator();
    AddCustomerService addCustomerService = new AddCustomerService(customerDatabase, addCustomerRequestValidator);

    DeleteCustomerRequestValidator deleteCustomerRequestValidator = new DeleteCustomerRequestValidator();
    DeleteCustomerService deleteCustomerService = new DeleteCustomerService(customerDatabase, deleteCustomerRequestValidator);

    GetAllCustomersService getAllCustomersService = new GetAllCustomersService(customerDatabase);

    FindAllCustomersByNameAndSurnameValidator findAllCustomersByNameAndSurnameValidator =
            new FindAllCustomersByNameAndSurnameValidator();
    FindAllCustomersByNameAndSurnameService findAllCustomersByNameAndSurnameService =
            new FindAllCustomersByNameAndSurnameService(customerDatabase, findAllCustomersByNameAndSurnameValidator);

    FindAllCustomersByNameValidator findAllCustomersByNameValidator = new FindAllCustomersByNameValidator();
    FindAllCustomersByNameService findAllCustomersByNameService =
            new FindAllCustomersByNameService(customerDatabase, findAllCustomersByNameValidator);

    FindAllCustomersBySurnameValidator findAllCustomersBySurnameValidator = new FindAllCustomersBySurnameValidator();
    FindAllCustomersBySurnameService findAllCustomersBySurnameService =
            new FindAllCustomersBySurnameService(customerDatabase, findAllCustomersBySurnameValidator);

    FindCustomerByIdRequestValidator findCustomerByIdRequestValidator = new FindCustomerByIdRequestValidator();
    FindCustomerByIdService findCustomerByIdService =
            new FindCustomerByIdService(customerDatabase, findCustomerByIdRequestValidator);



    public InternetStoreCustomerSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddCustomerUIAction(addCustomerService));
        menuNumberToAction.put(2, new DeleteCustomerUIAction(deleteCustomerService));
        menuNumberToAction.put(3, new PrintCustomersInfoUIAction(getAllCustomersService));
        menuNumberToAction.put(4, new FindCustomersByNameAndSurnameUIAction(findAllCustomersByNameAndSurnameService));
        menuNumberToAction.put(5, new FindAllCustomersByNameUIAction(findAllCustomersByNameService));
        menuNumberToAction.put(6, new FindAllCustomersBySurnameUIAction(findAllCustomersBySurnameService));
        menuNumberToAction.put(7, new FindCustomerByIdUIAction(findCustomerByIdService));
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
                "1   Sign in\n" +
                "2   Delete by id\n" +
                "3   Print customers\n" +
                "4   Find customer by name and surname\n" +
                "5   Find customers by name\n" +
                "6   Find customers by surname\n" +
                "7   Find all by id\n" +
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

