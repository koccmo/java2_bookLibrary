package lv.estore.app.userInterface;

import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.FindByIdResponse;
import lv.estore.app.core.services.FindByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindByIdUIAction implements  UIAction{

    @Autowired
    FindByIdService findByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Id': ");
        String id = scanner.nextLine();

        IdRequest request = new IdRequest(id);
        FindByIdResponse response = findByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().stream().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.hasProduct()) {
                System.out.println("Product found: " + response.getProduct().toString());
            } else {
                System.out.println("Product not found");
            }
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
