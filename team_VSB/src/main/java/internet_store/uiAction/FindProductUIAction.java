package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.requests.FindProductRequest;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.responses.FindProductResponse;
import internet_store.core.services.FindProductService;

import java.util.List;
import java.util.Scanner;

public class FindProductUIAction implements UIAction {

    private final FindProductService findProductService;

    public FindProductUIAction(FindProductService findProductService) { this.findProductService = findProductService; }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter product description: ");
        String productDescription = scanner.nextLine();

        System.out.println("Enter ordering. By 'product name' or by 'product description' ");
        String orderingType = scanner.nextLine();
        System.out.println("Enter ordering. By 'ascending' or by 'descending' ");
        String orderingDescription = scanner.nextLine();

        System.out.println("Enter number of product to display on page" );
        Integer pageSize = scanner.nextInt();
        System.out.println("Enter number of product page to show" );
        Integer pageNumber = scanner.nextInt();

        Ordering ordering = new Ordering(orderingType, orderingDescription);
        Paging paging = new Paging(pageSize, pageNumber);
        FindProductRequest request = new FindProductRequest(productName, productDescription, ordering, paging);
        FindProductResponse response = findProductService.execute(request);

        if (response.hasErrors()) {
            response.getError().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (!response.getProducts().isEmpty()) {
                System.out.println("Error" + response.getProducts().size() + " product(s) in database!");
                response.getProducts().forEach(System.out::println);
            } else {
                System.out.println("\n No product with name = " + productName + "found in database!");
            }
        }
    }
}
