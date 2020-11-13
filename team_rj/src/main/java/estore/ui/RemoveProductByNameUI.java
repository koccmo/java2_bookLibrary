package estore.ui;

import estore.database.ProductDataBase;

public class RemoveProductByNameUI implements UIAction {

    private ProductDataBase database;
    private InputValidation iv;

    public RemoveProductByNameUI(ProductDataBase database, InputValidation iv) {
        this.database = database;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product to remove: ";
        String productToRemove = iv.getString(description);
        int productsRemoved = database.removeProductByName(productToRemove);

        if (productsRemoved == 1) {
            System.out.println(productsRemoved + " product removed");
        } else {
            System.out.println(productsRemoved + " products removed");
        }
    }
}
