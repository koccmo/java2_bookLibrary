public class ProductApplication {
    public static void main(String[] args) {
        initializeEShop();
        printUserMenu();
    }

    public static void initializeEShop() {
        System.out.println("Welcome to the RedDots!");
    }

    public static void printUserMenu() {
        System.out.println("");
        System.out.println("Choose option by typing a valid number");
        System.out.println("1 - list of products");
        System.out.println("2 - find product by name");
        System.out.println("3 - add new product");
        System.out.println("4 - edit product data");
        System.out.println("5 - remove product");
        System.out.println("0 - exit");
    }
}
