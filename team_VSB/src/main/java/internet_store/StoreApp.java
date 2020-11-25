package internet_store;
/*
import java.util.Scanner;

public class StoreApp {
    private static final Database productRepository =
            Database.getDatabase();

    public static void main(String[] args) {
        long id;
        String name;
        String description;
        String command;
        double price;

        final Scanner scanner = new Scanner(System.in);
        Utils.showMenu();
        while (!(command = scanner.nextLine()).equals("exit")) {
            Utils.showMenu();
            switch (command) {
                case "1" -> {
                    System.out.println("Type product name: ");
                    name = scanner.nextLine();
                    System.out.println("Type product description: ");
                    description = scanner.nextLine();
                    System.out.println("Type price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    productRepository.addProduct(name, description, price);
                    System.out.println("Type \"Enter\" to continue.");
                }
                case "2" -> {
                    System.out.println("Find product by \"ID\": ");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Product found: ");
                    System.out.println(productRepository.findProductById(id));
                    System.out.println("Type \"Enter\" to continue.");
                }
                case "3" -> {
                    System.out.println("Find product by \"name\": ");
                    name = scanner.nextLine();
                    System.out.println("Product found: ");
                    System.out.println(productRepository.findProductByName(name));
                    System.out.println("Type \"Enter\" to continue.");
                }
                case "4" -> {
                    System.out.println("Type ID of the product you want to remove: ");
                    id = Long.parseLong(scanner.nextLine());
                    productRepository.removeProductById(id);
                    System.out.println("Type \"Enter\" to continue.");
                }
                case "5" -> {
                    System.out.println("Type ID of the product you want to update info: ");
                    id = Long.parseLong(scanner.nextLine());
                    System.out.println("Type the updated product name: ");
                    name = scanner.nextLine();
                    System.out.println("Type product updated description: ");
                    description = scanner.nextLine();
                    System.out.println("Type updated price: ");
                    price = Double.parseDouble(scanner.nextLine());
                    productRepository.updateProductById(id, name, description, price);
                    System.out.println("Type \"Enter\" to continue.");
                }
                case "6" -> {
                    System.out.println("The list of the products store contains: ");
                    productRepository.printOutAllProducts();
                    System.out.println("Type \"Enter\" to continue.");
                }
                default -> System.out.println("Please, type a valid option.");
            }
        }
    }
}


 */