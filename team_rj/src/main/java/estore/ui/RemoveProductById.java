package estore.ui;

import estore.database.ProductDataBase;

public class RemoveProductById implements UIAction {

    private ProductDataBase database;
    private InputValidation iv;

    public RemoveProductById(ProductDataBase database, InputValidation iv) {
        this.database = database;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter id of product to remove: ";
        int productToRemoveId = iv.getPositiveInteger(description);
        int productsRemoved = database.removeProductById(Long.valueOf(productToRemoveId));

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed.");
        } else {
            System.out.println(productsRemoved + " products removed.");
        }
    }
}
