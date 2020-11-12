package internet_store.lesson_2.ui;

import internet_store.lesson_2.services.AddProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final AddProductService addProductService;

    public AddProductUIAction(AddProductService addProductService) {
        this.addProductService = addProductService;
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

        addProductService.addProduct(productName, productDescription, productPrice);
        System.out.println("\nProduct added\n" + productName + "\n" + productDescription + "\n" + productPrice + " EUR");
    }
}
