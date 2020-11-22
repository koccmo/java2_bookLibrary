package internet_store.application.console_ui;

import internet_store.application.core.requests.SearchProductsRequest;
import internet_store.application.core.responses.SearchProductsResponse;
import internet_store.application.core.services.SearchProductsService;

import java.util.Scanner;

public class SearchProductsUIAction implements UIAction{

    private final SearchProductsService searchProductsService;

    public SearchProductsUIAction(SearchProductsService searchProductsService) {
        this.searchProductsService = searchProductsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        SearchProductsRequest request = new SearchProductsRequest(name, description);
        SearchProductsResponse response = searchProductsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {

            if (!response.getProducts().isEmpty()){
                System.out.println("\nFound " + response.getProducts().size() + " product(s) in the database : ");
                response.getProducts().forEach(System.out::println);
            } else {
                System.out.println("\nNo product with name = " + name + " found in the database");

            }
//            response.getProducts().forEach(Product::toString);
        }
    }

}
