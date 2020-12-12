package internet_store.application.console_ui;

import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.DeleteByProductIdResponse;
import internet_store.application.core.services.DeleteByProductIdService;
import internet_store.application.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteByIdUIAction implements UIAction {

    @DIDependency private DeleteByProductIdService deleteByProductIdService;


    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        Long productId = myInput.nextLong();
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(productId);
        DeleteByProductIdResponse response = deleteByProductIdService.execute(request);

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
