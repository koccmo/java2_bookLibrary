package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.ChangeProductNameRequest;
import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.ChangeProductNameResponse;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.ChangeProductNameService;
import internet_store.application.core.services.FindByIdService;
import internet_store.application.dependency_injection.DIComponent;
import internet_store.application.dependency_injection.DIDependency;

import java.util.Optional;
import java.util.Scanner;

@DIComponent
public class ChangeProductNameUIAction implements UIAction {

    @DIDependency private ChangeProductNameService changeProductNameService;
    @DIDependency private FindByIdService findByIdService;


    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        String id = myInput.nextLine();

        FindByIdRequest idRequest = new FindByIdRequest(id);
        FindByIdResponse idResponse = findByIdService.execute(idRequest);

        Optional<Product> foundProduct = idResponse.getProductFoundById();

        if (idResponse.hasErrors()) {
            idResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else { if (idResponse.getProductFoundById().isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            Product product = foundProduct.get();
            System.out.println("Found product in the database :");
            System.out.print(product.toString() + "\n");
        }
        }

        System.out.print("Enter new name for product: ");
        String newName = myInput.nextLine();
        ChangeProductNameRequest changeProductNameRequest = new ChangeProductNameRequest (foundProduct.get().getId(),
                                                                                          newName);
        ChangeProductNameResponse changeProductNameResponse = changeProductNameService.execute(changeProductNameRequest);

        //boolean productNameChange = changeProductNameService.changeProductName(foundProduct.get().getId(), newName);

        if (changeProductNameResponse.isNameChanged()) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("Could not change name");
        }
    }

}
