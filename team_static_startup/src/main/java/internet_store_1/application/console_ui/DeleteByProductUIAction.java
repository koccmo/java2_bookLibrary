package internet_store_1.application.console_ui;

import internet_store_1.application.core.domain.Product;
import internet_store_1.application.core.services.DeleteProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeleteByProductUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteByProductUIAction(DeleteProductService deleteProductService) {
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
