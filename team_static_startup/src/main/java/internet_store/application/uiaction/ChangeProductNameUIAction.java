package internet_store.application.uiaction;

import internet_store.application.ProductDatabase;

import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public ChangeProductNameUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product Id to search for: ");
        Long id = myInput.nextLong();
        System.out.print("Enter new name for product: ");
        String name = myInput.next();

        boolean productFound = productDatabase.changeProductName(id, name);

        if (productFound) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("We couldn't find product with id " + id);
        }

//        List<Product> productFound = productDatabase.findByProductIDAndChangeName(id);
//
//        if (productFound.isEmpty()) {
//            System.out.println("\nNo product with ID = " + id + " in the database");
//        } else {
//            System.out.println("Found next product in the DataBase :");
//            System.out.print(productFound.toString() + "\n");
//        }
    }

}
