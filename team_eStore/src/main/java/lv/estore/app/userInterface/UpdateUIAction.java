package lv.estore.app.userInterface;

import lv.estore.app.core.request.UpdateRequest;
import lv.estore.app.core.responses.UpdateResponse;
import lv.estore.app.core.services.UpdateByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UpdateUIAction implements UIAction {

    @Autowired
    UpdateByIdService updateByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'ID' of the product you want to update info: ");
        String id = scanner.nextLine();
        System.out.println("Type the updated product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product updated description: ");
        String description = scanner.nextLine();
        System.out.println("Type updated price: ");
        String price = scanner.nextLine();

        UpdateRequest request = new UpdateRequest(id, name, description, price);
        UpdateResponse response = updateByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductUpdated()) {
                System.out.println("Product was updated");
            } else {
                System.out.println("Product was not updated");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
