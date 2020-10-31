package team_static_startup.igors_test.uiaction;

import team_static_startup.igors_test.Product;
import team_static_startup.igors_test.ProductDatabase;

import java.math.BigDecimal;
import java.util.Scanner;

public class SaveProductUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public SaveProductUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Adding product to database : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        System.out.print("Enter product description : ");
        String productDescription = myInput.nextLine();
        System.out.print("Enter product price : ");
        BigDecimal productPrice = myInput.nextBigDecimal();

        productDatabase.save(new Product(productName, productDescription, productPrice));
    }
}
