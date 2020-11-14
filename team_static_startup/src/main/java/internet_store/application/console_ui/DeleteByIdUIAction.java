package internet_store.application.console_ui;

import internet_store.application.core.services.DeleteProductService;

import java.util.Scanner;

public class DeleteByIdUIAction implements UIAction {

    private final DeleteProductService deleteProductService;

    public DeleteByIdUIAction(DeleteProductService deleteProductService) {
        this.deleteProductService = deleteProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for deleting: ");
        Long id = myInput.nextLong();
        boolean productDeleted = deleteProductService.delete(id);

        if (productDeleted) {
            System.out.println("\nProduct with ID = " + id + " deleted");
        } else {
            System.out.println("\nProduct with ID = " + id + " is not in the database");
        }
    }

}
