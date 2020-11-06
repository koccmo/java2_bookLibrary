package internet_store.application.ui;

import internet_store.application.database.Database;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private final Database database;

    public DeleteByIdUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        Long id = myInput.nextLong();
        boolean productDeleted = database.delete(id);

        if (productDeleted) {
            System.out.println("\nProduct with ID = " + id + " deleted");
        } else {
            System.out.println("\nProduct with ID = " + id + " is not in the database");
        }
    }

}
