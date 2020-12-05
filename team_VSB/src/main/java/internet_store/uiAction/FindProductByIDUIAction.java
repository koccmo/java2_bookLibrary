package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.requests.FindProductByIDRequest;
import internet_store.core.responses.FindByIDResponse;
import internet_store.core.services.FindByIdService;
import internet_store.core.services.FindProductService;

import java.util.Optional;
import java.util.Scanner;

public class FindProductByIDUIAction implements UIAction {

    private final FindByIdService findByIdService;

    public FindProductByIDUIAction(FindByIdService findByIdService) { this.findByIdService = findByIdService; }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID for start searching: ");
        String id = scanner.nextLine();

        FindProductByIDRequest request = new FindProductByIDRequest(id);
        FindByIDResponse response = findByIdService.findById(request);

        Optional<Product> findProduct = response.getProductFindById();

        if (response.hasErrors()) {
            response.getError().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.getProductFindById().isEmpty()) {
                System.out.println("No product with this ID = '" + id + "' in database.");
            } else {
                Product product = findProduct.get();
                System.out.println("Found next product in database:  ");
                System.out.println(product.toString() + "\n");
            }
        }
    }
}
