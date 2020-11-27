package internet_store.lesson_4.console_ui;

import internet_store.lesson_4.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_4.core.responses.DeleteByProductIdResponse;
import internet_store.lesson_4.core.services.DeleteByProductIdService;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private final DeleteByProductIdService deleteByProductIdService;

    public DeleteByIdUIAction(DeleteByProductIdService deleteByProductIdService) {
        this.deleteByProductIdService = deleteByProductIdService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        String productId = myInput.nextLine();
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(productId);
        DeleteByProductIdResponse response = deleteByProductIdService.deleteByProductId(request);

        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getField()));
        } else {
            if (response.isProductRemoved()){
                System.out.println("\nProduct with Id = " + productId + " deleted");
            } else {
                System.out.println("\nProduct with Id = " + productId + " was NOT deleted");
            }
        }
    }

}
