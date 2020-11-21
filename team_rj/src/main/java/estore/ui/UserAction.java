package estore.ui;

import estore.core.validation.AddNewProductValidator;
import estore.core.validation.RemoveProductByIdValidator;
import estore.core.validation.RemoveProductByNameValidator;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductDataBase;
import estore.database.ProductDataBaseImplementation;
import estore.core.service.*;

public class UserAction {
    private static UserMenuChoiceValidation iv = new UserMenuChoiceValidation();
    private static AddNewProductValidator addNewProductValidator = new AddNewProductValidator();
    private static RemoveProductByIdValidator removeProductByIdValidator = new RemoveProductByIdValidator();
    private static RemoveProductByNameValidator removeProductByNameValidator = new RemoveProductByNameValidator();
    private static SearchProductByNameValidator searchProductByNameValidator = new SearchProductByNameValidator();
    private static ProductDataBase database = new ProductDataBaseImplementation();
    private static AddNewProductService addNewProductService = new AddNewProductService(database, addNewProductValidator);
    private static RemoveProductByIdService removeProductByIdService = new RemoveProductByIdService(database, removeProductByIdValidator);
    private static RemoveProductByNameService removeProductByNameService = new RemoveProductByNameService(database, removeProductByNameValidator);
    private static SearchProductByNameService searchProductByNameService = new SearchProductByNameService(database, searchProductByNameValidator);
    private static ShowAllProductsService showAllProductsService = new ShowAllProductsService(database);
    private static UIAction addNewProductUI = new AddProductUI(addNewProductService);
    private static UIAction removeProductById = new RemoveProductByIdUI(removeProductByIdService);
    private static UIAction removeProductByName = new RemoveProductByNameUI(removeProductByNameService);
    private static UIAction searchProductByName = new SearchProductByNameUI(searchProductByNameService);
    private static UIAction showAllProducts = new ShowAllProductsUI(showAllProductsService);
    private static UIAction exitProgram = new ExitProgramUI();

    public void run() {
        UserMenu userMenu = new UserMenu();
        this.printGreeting();
        while (true) {
            System.out.println("");
            System.out.println("Choose option by typing a valid number");
            userMenu.printUserMenu();
            int userInput = iv.getUserInputOfMenuItem(userMenu.getUserMenuSize());
            executeMenuItem(userInput);
        }
    }

    private void printGreeting() {
        System.out.println("Welcome to RedDots!");
    }

    private void executeMenuItem(int menuItem) {
        switch (menuItem) {
            case 1:
                showAllProducts.execute();
                break;
            case 2:
                searchProductByName.execute();
                break;
            case 3:
                addNewProductUI.execute();
                break;
            case 4:
                System.out.println("Not emplemented");
                break;
            case 5:
                removeProductByName.execute();
                break;
            case 6:
                removeProductById.execute();
                break;
            case 0:
                exitProgram.execute();
                break;
        }
    }
}
