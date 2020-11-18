package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.services.FindProductService;

import java.util.Optional;
import java.util.Scanner;

public class FindProductByIDUIAction implements UIAction {

    private final FindProductService findProductService;

    public FindProductByIDUIAction(FindProductService findProductService) { this.findProductService = findProductService; }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID for start searching: ");
        Long id = scanner.nextLong();
        Optional<Product> productOptional = findProductService.findById(id);

        if (productOptional.isEmpty()) {
            System.out.println("No product with this ID = '" + id + "' in database.");
        } else {
            System.out.println("Found next product in database:  ");
            System.out.println(productOptional.toString() + "\n");
        }
    }
}
