package lv.estore.app.console_ui.products;

import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.services.products.FindProductsByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindByNameUIAction implements UIAction{

    @Autowired
    FindProductsByNameService findProductsByNameService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Name': ");
        String name = scanner.nextLine();

        ProductNameRequest request = new ProductNameRequest(name);
        GetProductsResponse response = findProductsByNameService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().stream().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.hasProducts()) {
                response.getProducts().forEach(System.out::println);
            } else {
                System.out.println("Product with such name does not persist in database");
            }
        }
        System.out.println("Type 'Enter' to continue.");
    }
}
