package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.AddProductRequest;
import lv.estore.app.core.responses.products.AddProductResponse;
import lv.estore.app.core.services.products.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUIAction implements UIAction{

    @Autowired
    AddProductService addProductService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product description: ");
        String description = scanner.nextLine();
        System.out.println("Type price: ");
        String price = scanner.nextLine();

        AddProductRequest addProductRequest = new AddProductRequest(name, description, price);
        AddProductResponse response = addProductService.execute(addProductRequest);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("Product was added: " + response.isProductAdded());
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
