package internet_store.application.ui;

import internet_store.application.database.Database;

import java.util.Scanner;

public class DeleteByProductNameUIAction implements UIAction {

    private final Database database;

    public DeleteByProductNameUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();

        database.deleteByProductName(productName);
    }

}
