package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.FindProductByIdResponse;
import lv.estore.app.core.services.products.FindProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindByIdUIAction implements  UIAction{

    @Autowired
    FindProductByIdService findProductByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Id': ");
        String id = scanner.nextLine();

        ProductIdRequest request = new ProductIdRequest(id);
        FindProductByIdResponse response = findProductByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().stream().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.hasProduct()) {
                System.out.println("Product found: " + response.getProduct().toString());
            } else {
                System.out.println("Product not found");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
