package internet_store.uiAction;

import internet_store.core.requests.AddProductRequest;
import internet_store.core.responses.AddProductResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.AddProductService;

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
        AddProductRequest request = new AddProductRequest(productName, productDescription, productPrice);
        AddProductResponse response = addProductService.execute(request);

        if (response.hasErrors()) {
            response.getError().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("Product added: " + "\n" + productName + "\n" + productDescription + "\n" +
                    productPrice + "Euro." );
        }
    }
}
