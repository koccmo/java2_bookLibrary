package internet_store.uiAction;

import internet_store.core.requests.DeleteProductByIdRequest;
import internet_store.core.responses.DeleteProductByIdResponse;
import internet_store.core.services.DeleteProductByIdService;

import java.util.Scanner;

public class DeleteProductByIDUIAction implements UIAction {

    private final DeleteProductByIdService deleteProductByIdService;

    public DeleteProductByIDUIAction(DeleteProductByIdService deleteProductByIdService) {
        this.deleteProductByIdService = deleteProductByIdService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID for deleting: ");
        Long productId = scanner.nextLong();
        DeleteProductByIdRequest request = new DeleteProductByIdRequest(productId);
        DeleteProductByIdResponse response = deleteProductByIdService.deleteProductById(request);

        if (response.hasErrors()) {
            response.getError().forEach(coreError ->
                    System.out.println("Error " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductDeleted()) {
                System.out.println("Product with this ID '" + productId + "' is deleted successfully!.");
            } else {
                System.out.println("Product with this ID '" + productId + "' was not deleted!");
            }
        }
    }
}
