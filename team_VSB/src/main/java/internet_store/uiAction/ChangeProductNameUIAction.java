package internet_store.uiAction;

import internet_store.core.services.ChangeProductNameService;

import java.util.Scanner;

public class ChangeProductNameUIAction implements UIAction {

    private final ChangeProductNameService changeProductNameService;

    public ChangeProductNameUIAction(ChangeProductNameService changeProductNameService) {
        this.changeProductNameService = changeProductNameService;
    }


    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to start searching: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter new name for product: ");
        String newName = scanner.nextLine();

        boolean foundProduct = changeProductNameService.changeProductName(id, newName);

        if (foundProduct) {
            System.out.println("Name has been changed successfully!");
        } else {
            System.out.println("We can't find product with this '" + id +"'.");
        }
    }
}
