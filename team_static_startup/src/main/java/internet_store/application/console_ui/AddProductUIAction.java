package internet_store.application.console_ui;

import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.responses.AddProductResponse;
import internet_store.application.core.services.AddProductService;
import internet_store.application.dependency_injection.DIComponent;
import internet_store.application.dependency_injection.DIDependency;

import java.math.BigDecimal;
import java.util.Scanner;

@DIComponent
public class AddProductUIAction implements UIAction {

    @DIDependency private AddProductService addProductService;

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
