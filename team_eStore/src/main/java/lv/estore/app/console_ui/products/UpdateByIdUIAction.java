package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.UpdateProductByIdRequest;
import lv.estore.app.core.responses.products.UpdateProductResponse;
import lv.estore.app.core.services.products.UpdateProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateByIdUIAction implements UIAction {

    @Autowired
    UpdateProductByIdService updateProductByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'ID' of the product you want to update info: ");
        String id = scanner.nextLine();
        System.out.println("Type the updated product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product updated description: ");
        String description = scanner.nextLine();
        System.out.println("Type updated price: ");
        String price = scanner.nextLine();

        UpdateProductByIdRequest request = new UpdateProductByIdRequest(id, name, description, price);
        UpdateProductResponse response = updateProductByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductUpdated()) {
                System.out.println("Product was updated");
            } else {
                System.out.println("Product was not updated");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
