package estore.ui;

import estore.database.ProductDataBase;
import estore.database.ProductDataBaseImplementation;
import estore.domain.Product;

import java.util.List;
import java.util.Scanner;

public class UserAction {
    //private InputValidation iv = new InputValidation();
    //private ProductDataBase database = new ProductDataBaseImplementation();

    public void run() {
        InputValidation iv = new InputValidation();
        ProductDataBase database = new ProductDataBaseImplementation();

        this.printGreeting();
        while (true) {
            this.printUserMenu();
            int userInput = this.getUserInputOfMenuItem(iv);
            executeMenuItem(userInput, database, iv);
        }
    }

    private void printGreeting() {
        System.out.println("Welcome to RedDots!");
    }

    private void printGoodBye() {
        System.out.println("");
        System.out.println("Thanks for visiting RedDots!");
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

    private void executeMenuItem(int menuItem, ProductDataBase database, InputValidation iv) {
        switch (menuItem) {
            case 1:
                printListOfProducts(database.getDatabase());
                break;
            case 2:
                searchProductByName(database, iv);
                break;
            case 3:
                addNewProduct(database, iv);
                break;
            case 4:
                System.out.println("Not emplemented");
                break;
            case 5:
                removeProductByName(database, iv);
                break;
            case 6:
                removeProductById(database, iv);
                break;
            case 0:
                exitProgram();
                break;
        }
    }

    public int getUserInputOfMenuItem(InputValidation iv) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.print("Choice: ");
            String userStringInput = sc.nextLine();
            userInput = iv.validateUserMenuChoice(userStringInput);
            if (userInput != -1) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return userInput;
    }

    private void printListOfProducts(List<Product> products) {
        System.out.println("");
        System.out.println("List of products");
        if (products.size() == 0) {
            System.out.println("No products available");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public String getString(String description, InputValidation iv) {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String inputString = "";
        while (true) {
            System.out.println(description);
            inputString = sc.nextLine();
            if (iv.validateString(inputString)) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return inputString;
    }

    private void searchProductByName(ProductDataBase database, InputValidation iv) {
        String description = "Enter name of product to search: ";
        String productToSearch = getString(description, iv);
        List<Product> foundProducts = database.searchProductByName(productToSearch);
        printListOfProducts(foundProducts);
    }

    public String getLine(String description, InputValidation iv) {
        Scanner sc = new Scanner(System.in);
        String inputLine = "";
        while (true) {
            System.out.println(description);
            inputLine = sc.nextLine();
            if (iv.validateLine(inputLine)) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return inputLine;
    }

    private void addNewProduct(ProductDataBase database, InputValidation iv) {
        String description = "Enter name of product";
        String productName = getString(description, iv);
        description = "Enter description of product";
        String productDescription = getLine(description, iv);
        Product product = new Product(productName, productDescription);
        if (database.addNewProduct(product)) {
            System.out.println("Seccessfully added");
        } else {
            System.out.println("Product has not been added");
        }
    }

    private void removeProductByName(ProductDataBase database, InputValidation iv) {
        String description = "Enter name of product to remove: ";
        String productToRemove = getString(description, iv);
        int productsRemoved = database.removeProductByName(productToRemove);

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed");
        } else {
            System.out.println(productsRemoved + " products removed");
        }
    }

    public int getPositiveInteger(String description, InputValidation iv) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        while (true) {
            System.out.print("Choice: ");
            String userStringInput = sc.nextLine();
            userInput = iv.validatePositiveInteger(userStringInput);
            if (userInput != -1) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return userInput;
    }

    private void removeProductById(ProductDataBase database, InputValidation iv) {
        String description = "Enter id of product to remove: ";
        int productToRemoveId = getPositiveInteger(description, iv);
        int productsRemoved = database.removeProductById(Long.valueOf(productToRemoveId));

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed");
        } else {
            System.out.println(productsRemoved + " products removed");
        }
    }

    private void exitProgram() {
        this.printGoodBye();
        System.exit(0);
    }
}
