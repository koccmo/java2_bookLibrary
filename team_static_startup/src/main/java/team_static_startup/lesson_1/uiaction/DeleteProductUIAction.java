package team_static_startup.lesson_1.uiaction;

import team_static_startup.lesson_1.Product;
import team_static_startup.lesson_1.ProductDatabase;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeleteProductUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public DeleteProductUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
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

        boolean productDeleted = productDatabase.delete(new Product(productName, productDescription, productPrice));
        if (productDeleted) {
            System.out.println("Product deleted");
        } else {
            System.out.println("There is no such product in the database");
        }
    }

}
