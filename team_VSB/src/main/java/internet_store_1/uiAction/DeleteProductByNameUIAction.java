package internet_store_1.uiAction;

import internet_store_1.core.requests.DeleteProductByNameRequest;
import internet_store_1.core.responses.DeleteProductByNameResponse;
import internet_store_1.core.services.DeleteProductService;

import java.util.Scanner;

public class DeleteProductByNameUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteProductByNameUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name to deleting: ");
        String productName = scanner.nextLine();
        DeleteProductByNameRequest request = new DeleteProductByNameRequest(productName);
        DeleteProductByNameResponse response = deleteProductService.deleteProductByName(request);

        if (response.hasErrors()) {
            response.getError().forEach(coreError ->
                    System.out.println("Error " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductDeleted()) {
                System.out.println("Product with this name '" + productName + "' is successfully!");
            } else {
                System.out.println("Product with this name '" + productName + "' was not deleted!");
            }
        }
    }

}
