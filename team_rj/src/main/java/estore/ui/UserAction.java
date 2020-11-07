package estore.ui;

import estore.database.ProductDataBase;
import estore.database.ProductDataBaseImplementation;

public class UserAction {
    private static InputValidation iv = new InputValidation();
    private static  ProductDataBase database = new ProductDataBaseImplementation();
    private static UIAction addNewProduct = new AddProduct(database, iv);
    private static UIAction removeProductByName = new RemoveProductByName(database, iv);
    private static UIAction removeProductById = new RemoveProductById(database, iv);
    private static UIAction searchProductByName = new SearchProductByName(database, iv);
    private static UIAction showAllProducts = new ShowAllProducts(database);
    private static UIAction exitProgram = new ExitProgram();

    public void run() {
        this.printGreeting();
        while (true) {
            this.printUserMenu();
            int userInput = iv.getUserInputOfMenuItem();
            executeMenuItem(userInput);
        }
    }

    private void printGreeting() {
        System.out.println("Welcome to RedDots!");
    }

    private void printUserMenu() {
        System.out.println("");
        System.out.println("Choose option by typing a valid number");
        System.out.println("1 - list of products");
        System.out.println("2 - find product by name");
        System.out.println("3 - add new product");
        System.out.println("4 - edit product data");
        System.out.println("5 - remove product by name");
        System.out.println("6 - remove product by id");
        System.out.println("0 - exit");
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
                addNewProduct.execute();
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
