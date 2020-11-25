package internet_store_1.uiAction;

import internet_store_1.core.services.AddProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final AddProductService addProductService;

    public AddProductUIAction(AddProductService addProductService) { this.addProductService = addProductService; }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add product to database: ");
        System.out.printf("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter product description: ");
        String productDescription = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal productPrice = scanner.nextBigDecimal();

        addProductService.addProduct(productName, productDescription, productPrice);
        System.out.println("Product added: " + "\n" + productName + "\n" + productDescription + "\n" + productPrice + "Euro." );
    }
}
