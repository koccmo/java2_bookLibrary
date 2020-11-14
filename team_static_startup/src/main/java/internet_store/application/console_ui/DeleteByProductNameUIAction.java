package internet_store.application.console_ui;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.DeleteProductService;

import java.util.Scanner;

public class DeleteByProductNameUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteByProductNameUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(productName);
        DeleteByProductNameResponse response = deleteProductService.deleteByProductName(request);

        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getField()));
        } else {
            if (response.isBookRemoved()){
                System.out.println("\nProduct with name = " + productName + " deleted");
            } else {
                System.out.println("\nProduct with name = " + productName + " was NOT deleted");
            }
        }
    }

}
