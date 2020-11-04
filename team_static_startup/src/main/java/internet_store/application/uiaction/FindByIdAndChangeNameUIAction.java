package internet_store.application.uiaction;

import internet_store.application.Product;
import internet_store.application.ProductDatabase;

import java.util.List;
import java.util.Scanner;

public class FindByIdAndChangeNameUIAction implements UIAction{

    private final ProductDatabase productDatabase;

    public FindByIdAndChangeNameUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product Id to search for: ");
        Long id = myInput.nextLong();

        List<Product> productFound = productDatabase.findByProductIDAndChangeName(id);

        if (productFound.isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the database");
        } else {
            System.out.println("Found next product in the DataBase :");
            System.out.print(productFound.toString() + "\n");
        }
    }

}
