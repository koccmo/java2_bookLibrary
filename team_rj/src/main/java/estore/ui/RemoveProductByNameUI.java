package estore.ui;

import estore.database.ProductDataBase;
import estore.service.RemoveProductByNameService;

public class RemoveProductByNameUI implements UIAction {

    private RemoveProductByNameService removeProductByNameService;
    private InputValidation iv;

    public RemoveProductByNameUI(RemoveProductByNameService removeProductByNameService, InputValidation iv) {
        this.removeProductByNameService = removeProductByNameService;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to remove: ";
        String productToRemove = iv.getString(description);
        int productsRemoved = removeProductByNameService.execute(productToRemove);

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed");
        } else {
            System.out.println(productsRemoved + " products removed");
        }
    }
}
