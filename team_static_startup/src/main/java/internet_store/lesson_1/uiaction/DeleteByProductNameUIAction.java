package internet_store.lesson_1.uiaction;

import internet_store.lesson_1.ProductDatabase;

import java.util.Scanner;

public class DeleteByProductNameUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public DeleteByProductNameUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();

        productDatabase.deleteByProductName(productName);
    }

}
