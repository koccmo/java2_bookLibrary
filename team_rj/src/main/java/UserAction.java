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
            int userInput = this.getUserInput();

            switch (userInput) {
                case 1:
                    printProducts(products);
                    break;
                case 2:
                    searchProductByName(products);
                    break;
                case 3:
                    System.out.println("Add new product");
                    break;
                case 4:
                    System.out.println("Edit product data");
                    break;
                case 5:
                    System.out.println("Remove product");
                    break;
                case 0:
                    this.printGoodBye();
                    System.exit(0);
            }
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

    public int getUserInput() {
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

    public String getProductNameForSearch(List<Product> products) {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String productName = "";
        while (true) {
            System.out.print("Enter name of product to search: ");
            productName = sc.nextLine();
            if (iv.validateString(productName)) {
                break;
            }
            System.out.println("Invalid input.");
        }
        return productName;
    }

    public void searchProductByName(List<Product> products) {
        String productToSearch = getProductNameForSearch(products);
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(productToSearch)) {
                foundProducts.add(product);
            }
        }
        printProducts(foundProducts);
    }

}
