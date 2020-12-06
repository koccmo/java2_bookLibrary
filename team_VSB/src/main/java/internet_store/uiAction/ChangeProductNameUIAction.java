package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.requests.ChangeProductNameRequest;
import internet_store.core.requests.FindProductByIDRequest;
import internet_store.core.responses.ChangeProductNameResponse;
import internet_store.core.responses.FindByIDResponse;
import internet_store.core.services.ChangeProductNameService;
import internet_store.core.services.FindByIdService;

import java.util.Optional;
import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final ChangeProductNameService changeProductNameService;
    private final FindByIdService findByIdService;

    public ChangeProductNameUIAction(ChangeProductNameService changeProductNameService, FindByIdService findByIdService) {
        this.changeProductNameService = changeProductNameService;
        this.findByIdService = findByIdService;
    }


    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to start searching: ");
        String id = scanner.nextLine();

        FindProductByIDRequest idRequest = new FindProductByIDRequest(id);
        FindByIDResponse idResponse = findByIdService.findById(idRequest);

        Optional<Product> findProduct = idResponse.getProductFindById();

        if (idResponse.hasErrors()) {
            idResponse.getError().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (idResponse.getProductFindById().isEmpty()) {
                System.out.println("Product with this id: '" + id + "', don't exist in Database");
            } else {
                Product product = findProduct.get();
                System.out.println("Find product in Database: ");
                System.out.println(product.toString() + "\n");
            }
        }

        System.out.println("Enter product new name: ");
        String newName = scanner.nextLine();
        ChangeProductNameRequest changeProductNameRequest = new ChangeProductNameRequest(findProduct.get().getId(),
                                                                                         newName);
        ChangeProductNameResponse changeProductNameResponse = changeProductNameService.changeProductName(changeProductNameRequest);

        if (changeProductNameResponse.isChangeName()) {
            System.out.println("Product name has been changed!");
        } else  {
            System.out.println("Can't change name.");
        }
    }
}
