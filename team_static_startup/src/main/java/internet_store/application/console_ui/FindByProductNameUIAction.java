package internet_store.application.console_ui;

import internet_store.application.core.requests.FindByProductNameRequest;
import internet_store.application.core.responses.FindByProductNameResponse;
import internet_store.application.core.services.FindProductService;

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
        FindByProductNameRequest request = new FindByProductNameRequest(productName);
        FindByProductNameResponse response = findProductService.findByProductName(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getField()));
        } else {
            if (!response.getProductNameList().isEmpty()){
                System.out.println("\nFound " + response.getProductNameList().size() + " product(s) in the database : ");
                response.getProductNameList().forEach(System.out::println);
            } else {
                System.out.println("\nNo product with name = " + productName + " found in the database");

            }

        }
    }

}