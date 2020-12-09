package internet_store.application.console_ui;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.DeleteByProductNameService;
import internet_store.application.dependency_injection.DIComponent;
import internet_store.application.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteByProductNameUIAction implements UIAction {

    @DIDependency private DeleteByProductNameService deleteProductByNameService;


    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(productName);
        DeleteByProductNameResponse response = deleteProductByNameService.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductRemoved()){
                System.out.println("\nProduct with name = " + productName + " deleted");
            } else {
                System.out.println("\nProduct with name = " + productName + " was NOT deleted");
            }
        }
    }

}
