package internet_store.lesson_6.console_ui;

import internet_store.lesson_6.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_6.core.responses.DeleteByProductNameResponse;
import internet_store.lesson_6.core.services.DeleteByProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteByProductNameUIAction implements UIAction {

    @Autowired
    private DeleteByProductNameService deleteProductByNameService;


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
