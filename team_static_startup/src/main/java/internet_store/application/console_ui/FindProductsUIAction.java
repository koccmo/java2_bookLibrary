package internet_store.application.console_ui;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.responses.FindProductsResponse;
import internet_store.application.core.services.FindProductsService;

import java.util.Scanner;

public class FindProductsUIAction implements UIAction{

    private final FindProductsService findProductsService;

    public FindProductsUIAction(FindProductsService findProductsService) {
        this.findProductsService = findProductsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        FindProductsRequest request = new FindProductsRequest(name, description);
        FindProductsResponse response = findProductsService.execute(request);

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
