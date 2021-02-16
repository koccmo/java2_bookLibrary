package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.services.products.RemoveProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveByIdUIAction implements UIAction {

    @Autowired
    RemoveProductByIdService removeProductByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'Id' of the product you want to remove: ");
        String id = scanner.nextLine();

        ProductIdRequest request = new ProductIdRequest(id);
        RemoveProductResponse response = removeProductByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductRemoved()) {
                System.out.println("Product was removed");
            } else {
                System.out.println("Product was not removed");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
