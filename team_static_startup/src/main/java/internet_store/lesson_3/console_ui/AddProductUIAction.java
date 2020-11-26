package internet_store.lesson_3.console_ui;

import internet_store.lesson_3.core.requests.AddProductRequest;
import internet_store.lesson_3.core.responses.AddProductResponse;
import internet_store.lesson_3.core.services.AddProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final AddProductService addProductService;

    public AddProductUIAction(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Adding product to database : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        System.out.print("Enter product description : ");
        String productDescription = myInput.nextLine();
        System.out.print("Enter product price : ");
        BigDecimal productPrice = myInput.nextBigDecimal();
        AddProductRequest request = new AddProductRequest(productName, productDescription, productPrice);
        AddProductResponse response = addProductService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("\nProduct added\n" + productName + "\n" + productDescription + "\n" + productPrice + " EUR");
        }
    }

}
