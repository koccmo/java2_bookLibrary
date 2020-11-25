package internet_store_1.uiAction;

import internet_store_1.core.domain.Product;
import internet_store_1.core.requests.FindProductByNameRequest;
import internet_store_1.core.services.FindProductService;

import java.util.List;
import java.util.Scanner;

public class FindProductByNameUIAction implements UIAction {

    private final FindProductService findProductService;

    public FindProductByNameUIAction(FindProductService findProductService) { this.findProductService = findProductService; }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name for start searching: ");
        String productName = scanner.nextLine();
        FindProductByNameRequest request = new FindProductByNameRequest(productName);
        List<Product> productFound = findProductService.findByNameProduct(request);

        if (productFound.isEmpty()) {
            System.out.println("No product with name: '" + productFound + "' in database.");
        } else {
            System.out.println("Found '" + productFound.size() + "' product(s) in database: ");
            for (Product products : productFound) {
                System.out.println(toString() + "\n");
            }
        }
    }
}
