package internet_store.uiAction;

import internet_store.core.domain.Product;
import internet_store.core.services.DeleteProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeleteProductUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteProductUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deleting product by name, description and price: ");
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.println("Enter product description: ");
        String productDescription = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal productPrice = scanner.nextBigDecimal();

        boolean productDeleted = deleteProductService.delete(new Product(productName, productDescription, productPrice));
        if (productDeleted) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product was not deleted!");
        }
    }
}
