package internet_store.lesson_3.console_ui;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.requests.ChangeProductNameRequest;
import internet_store.lesson_3.core.requests.FindByIdRequest;
import internet_store.lesson_3.core.responses.ChangeProductNameResponse;
import internet_store.lesson_3.core.responses.FindByIdResponse;
import internet_store.lesson_3.core.services.ChangeProductNameService;
import internet_store.lesson_3.core.services.FindByIdService;

import java.util.Optional;
import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final ChangeProductNameService changeProductNameService;
    private final FindByIdService findByIdService;

    public ChangeProductNameUIAction(ChangeProductNameService changeProductNameService,
                                     FindByIdService findByIdService) {
        this.changeProductNameService = changeProductNameService;
        this.findByIdService = findByIdService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        String id = myInput.nextLine();

        FindByIdRequest idRequest = new FindByIdRequest(id);
        FindByIdResponse idResponse = findByIdService.findById(idRequest);

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
        ChangeProductNameResponse changeProductNameResponse = changeProductNameService.changeProductName(changeProductNameRequest);

        //boolean productNameChange = changeProductNameService.changeProductName(foundProduct.get().getId(), newName);

        if (changeProductNameResponse.isNameChanged()) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("Could not change name");
        }
    }

}
