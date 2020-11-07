package internet_store.application.ui;

import internet_store.application.domain.Product;
import internet_store.application.database.Database;
import internet_store.application.services.FindProductService;

import java.util.List;
import java.util.Scanner;

public class FindByProductNameUIAction implements UIAction {

    private final FindProductService findProductService;

    public FindByProductNameUIAction(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product name to search for: ");
        String productName = myInput.nextLine();

        List<Product> productFound = findProductService.findByProductName(productName);

        if (productFound.isEmpty()) {
            System.out.println("\nNo product with name = " + productName + " in the database");
        } else {
            System.out.println("\nFound " + productFound.size() + " product(s) in the database : ");
            for (Product product : productFound) {
                System.out.print(product.toString() + "\n");
            }
        }
    }

}