package lv.estore.app.userInterface;

import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.services.RemoveByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveByNameUIAction implements UIAction {

    @Autowired
    RemoveByNameService removeByNameService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'Name' of the product you want to remove: ");
        String name = scanner.nextLine();

        NameRequest request = new NameRequest(name);
        RemoveResponse response = removeByNameService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isProductRemoved()) {
                System.out.println("Product was removed");
            } else {
                System.out.println("Product was not removed");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
