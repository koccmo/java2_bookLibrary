package internet_store.lesson_1.uiaction;

import internet_store.lesson_1.ProductDatabase;

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
        myInput.nextLine();
        System.out.print("Enter new name for product: ");
        String name = myInput.nextLine();

        boolean productFound = productDatabase.changeProductName(id, name);

        if (productFound) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("We couldn't find product with id " + id);
        }
    }

}
