package internet_store.application.ui;

import internet_store.application.domain.Product;
import internet_store.application.database.Database;

import java.math.BigDecimal;
import java.util.Scanner;

public class SaveProductUIAction implements UIAction {

    private final Database database;

    public SaveProductUIAction(Database database) {
        this.database = database;
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

        database.save(new Product(productName, productDescription, productPrice));
        System.out.println("\nProduct added\n" + productName + "\n" + productDescription + "\n" + productPrice + " EUR");
    }
}
