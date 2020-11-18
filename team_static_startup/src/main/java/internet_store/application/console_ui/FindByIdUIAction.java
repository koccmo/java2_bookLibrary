package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.FindProductService;

import java.util.List;
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

        FindByIdRequest request = new FindByIdRequest(id);
        FindByIdResponse response = findProductService.findById(request);

        List<Product> foundProduct = response.getProductFoundById();

        if (response.getProductFoundById().isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            System.out.println("Found next product in the DataBase :");
            System.out.print(foundProduct.get(0).toString() + "\n");
        }
    }

}
