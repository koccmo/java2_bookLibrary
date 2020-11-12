package internet_store.lesson_2.ui;

import internet_store.lesson_2.database.Database;
import internet_store.lesson_2.domain.Product;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final Database database;

    public AddProductUIAction(Database database) {
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

        database.add(new Product(productName, productDescription, productPrice));
        System.out.println("\nProduct added\n" + productName + "\n" + productDescription + "\n" + productPrice + " EUR");
    }
}
