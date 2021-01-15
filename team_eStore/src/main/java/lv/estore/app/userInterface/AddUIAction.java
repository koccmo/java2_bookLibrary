package lv.estore.app.userInterface;

import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.responses.AddResponse;
import lv.estore.app.core.services.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUIAction implements UIAction{

    @Autowired
    AddService addService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product description: ");
        String description = scanner.nextLine();
        System.out.println("Type price: ");
        String price = scanner.nextLine();

        AddRequest addRequest = new AddRequest(name, description, price);
        AddResponse response = addService.execute(addRequest);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            System.out.println("Product was added: " + response.isProductAdded());
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
