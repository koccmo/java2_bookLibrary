package internet_store.application.console_ui;

import internet_store.application.core.domain.Product;
import internet_store.application.core.services.DeleteProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeleteProductUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteProductUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name and description : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        System.out.print("Enter product description : ");
        String productDescription = myInput.nextLine();
        System.out.print("Enter product price : ");
        BigDecimal productPrice = myInput.nextBigDecimal();

        boolean productDeleted = deleteProductService.delete(new Product(productName, productDescription, productPrice));
        if (productDeleted) {
            System.out.println("\nProduct deleted");
        } else {
            System.out.println("\nThere is no such product in the database");
        }
    }

}
