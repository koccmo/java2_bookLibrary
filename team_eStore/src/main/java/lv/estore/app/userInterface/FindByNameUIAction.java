package lv.estore.app.userInterface;

import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.FindResponse;
import lv.estore.app.core.services.FindByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindByNameUIAction implements UIAction{

    @Autowired
    FindByNameService findByNameService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Name': ");
        String name = scanner.nextLine();

        NameRequest request = new NameRequest(name);
        FindResponse response = findByNameService.execute(request);

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
        System.out.println("Type 'Enter' to continue.");
    }
}
