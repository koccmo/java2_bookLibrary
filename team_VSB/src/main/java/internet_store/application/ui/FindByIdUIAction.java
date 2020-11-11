package internet_store.application.ui;

import internet_store.application.domain.Product;
import internet_store.application.services.FindProductService;

import java.util.Optional;
import java.util.Scanner;

public class FindByIdUIAction implements UIAction {

    private final FindProductService findProductService;

    public FindByIdUIAction(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        Long id = myInput.nextLong();
        Optional<Product> productOpt = findProductService.findById(id);

        if (productOpt.isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            System.out.println("Found next product in the DataBase :");
            System.out.print(productOpt.toString() + "\n");
        }
    }

}
