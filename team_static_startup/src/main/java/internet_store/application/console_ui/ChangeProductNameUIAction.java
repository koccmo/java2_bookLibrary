package internet_store.application.console_ui;

import internet_store.application.core.services.ChangeProductNameService;

import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final ChangeProductNameService changeProductNameService;

    public ChangeProductNameUIAction(ChangeProductNameService changeProductNameService) {
        this.changeProductNameService = changeProductNameService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product Id to search for: ");
        Long id = myInput.nextLong();
        myInput.nextLine();
        System.out.print("Enter new name for product: ");
        String newName = myInput.nextLine();

        boolean productFound = changeProductNameService.changeProductName(id, newName);

        if (productFound) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("We couldn't find product with id " + id);
        }
    }

}
