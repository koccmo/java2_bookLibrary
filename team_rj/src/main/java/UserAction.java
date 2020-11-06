import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAction {
    InputValidation iv = new InputValidation();
    private List<Product> products = initializeProducts();

    public void run() {
        this.printGreeting();
        while (true) {
            this.printUserMenu();
            int userInput = this.getUserInputOfMenuItem();
            executeMenuItem(products, userInput);
        }
    }

    public void printGreeting() {
        System.out.println("Welcome to RedDots!");
    }

    public void printGoodBye() {
        System.out.println("");
        System.out.println("Thanks for visiting RedDots!");
    }

    public void printUserMenu() {
        System.out.println("");
        System.out.println("Choose option by typing a valid number");
        System.out.println("1 - list of products");
        System.out.println("2 - find product by name");
        System.out.println("3 - add new product");
        System.out.println("4 - edit product data");
        System.out.println("5 - remove product");
        System.out.println("0 - exit");
    }

    public void executeMenuItem(List<Product> products, int menuItem) {
        switch (menuItem) {
            case 1:
                printProducts(products);
                break;
            case 2:
                searchProductByName(products);
                break;
            case 3:
                addNewProduct(products);
                break;
            case 4:
                System.out.println("Not emplemented");
                break;
            case 5:
                removeProduct(products);
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

    public List<Product> initializeProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Apple", "Juicy red apples"));
        products.add(new Product("Melon", "Melons from Georgia"));
        products.add(new Product("Grapes", "Small blue grapes"));
        return products;
    }

    public void printProducts(List<Product> products) {
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

    public void searchProductByName(List<Product> products) {
        String description = "Enter name of product to search: ";
        String productToSearch = getString(description);
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(productToSearch.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        printProducts(foundProducts);
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

    public void addNewProduct(List<Product> products) {
        String description = "Enter name of product";
        String productName = getString(description);
        description = "Enter description of product";
        String productDescription = getLine(description);
        products.add(new Product(productName, productDescription));
        System.out.println("Seccessfully added");
    }

    public void removeProduct(List<Product> products) {
        String description = "Enter name of product to remove: ";
        String productToRemove = getString(description);
        int productsRemoved = 0;

        for (int i = products.size() - 1; i >=0; i--) {
            if (products.get(i).getName().toLowerCase().equals(productToRemove.toLowerCase())) {
                products.remove(products.get(i));
                productsRemoved++;
            }
        }
        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed");
        } else {
            System.out.println(productsRemoved + " products removed");
        }
    }

    public void exitProgram() {
        this.printGoodBye();
        System.exit(0);
    }
}
