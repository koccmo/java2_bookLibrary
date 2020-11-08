package internet_store.application.ui;

import internet_store.application.database.Database;

import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final Database database;

    public ChangeProductNameUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product Id to search for: ");
        Long id = myInput.nextLong();
        myInput.nextLine();
        System.out.print("Enter new name for product: ");
        String name = myInput.nextLine();

        boolean productFound = database.changeProductName(id, name);

        if (productFound) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("We couldn't find product with id " + id);
        }
    }

}
