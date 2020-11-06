import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAction {
    private InputValidation iv = new InputValidation();
    private ProductDataBase database = new ProductDataBaseImplementation();

    public void run() {
        this.printGreeting();
        while (true) {
            this.printUserMenu();
            int userInput = this.getUserInputOfMenuItem();
            executeMenuItem(userInput);
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
        System.out.println("5 - remove product");
        System.out.println("0 - exit");
    }

    private void executeMenuItem(int menuItem) {
        switch (menuItem) {
            case 1:
                printAllProducts();
                break;
            case 2:
                searchProductByName();
                break;
            case 3:
                addNewProduct();
                break;
            case 4:
                System.out.println("Not emplemented");
                break;
            case 5:
                removeProduct();
                break;
            case 0:
                exitProgram();
                break;
        }
    }

    public int getUserInputOfMenuItem() {
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

    private void printAllProducts() {
        System.out.println("");
        System.out.println("List of products");
        if (!database.printProducts()) {
            System.out.println("No products available");
        }
    }

    private void printSpecificListOfProducts(List<Product> products) {
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

    public String getString(String description) {
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

    private void searchProductByName() {
        String description = "Enter name of product to search: ";
        String productToSearch = getString(description);
        List<Product> foundProducts = database.searchProductByName(productToSearch);
        printSpecificListOfProducts(foundProducts);
    }

    public String getLine(String description) {
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

    private void addNewProduct() {
        String description = "Enter name of product";
        String productName = getString(description);
        description = "Enter description of product";
        String productDescription = getLine(description);
        Product product = new Product(productName, productDescription);
        if (database.addNewProduct(product)) {
            System.out.println("Seccessfully added");
        } else {
            System.out.println("Product has not been added");
        }
    }

    private void removeProduct() {
        String description = "Enter name of product to remove: ";
        String productToRemove = getString(description);
        int productsRemoved = database.removeProduct(productToRemove);

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
