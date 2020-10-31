package team_static_startup.igors_test.uiaction;

import team_static_startup.igors_test.Product;
import team_static_startup.igors_test.ProductDatabase;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public DeleteByIdUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        Long id = myInput.nextLong();
        boolean productDeleted = productDatabase.delete(id);

        if (productDeleted) {
            System.out.println("Product with ID = " + id + " deleted");
        } else {
            System.out.println("Product with ID = " + id + " is not in the database");
        }
    }

}
