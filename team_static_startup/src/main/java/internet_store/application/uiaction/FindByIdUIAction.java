package internet_store.application.uiaction;

import internet_store.application.Product;
import internet_store.application.ProductDatabase;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class FindByIdUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public FindByIdUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        Long id = myInput.nextLong();
        Optional<Product> productOpt = productDatabase.findById(id);

        if (productOpt.isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            System.out.println("Found next product in the DataBase :");
            System.out.println("Product name: " + productOpt.get().getName());
            System.out.println("Product description: " + productOpt.get().getDescription());
/*
            System.out.println("Changing product in the database : ");
            System.out.print("Enter product name : ");
            String productName = myInput.nextLine();
            System.out.print("Enter product description : ");
            String productDescription = myInput.nextLine();
*/
        }

    }

}
