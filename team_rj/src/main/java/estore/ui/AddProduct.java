package estore.ui;

import estore.database.ProductDataBase;
import estore.domain.Product;

public class AddProduct implements UIAction {

    private ProductDataBase database;
    private InputValidation iv;

    public AddProduct(ProductDataBase database, InputValidation iv) {
        this.database = database;
        this.iv = iv;
    }

    @Override
    public void execute() {
        String description = "Enter name of product";
        String productName = iv.getString(description);
        description = "Enter description of product";
        String productDescription = iv.getLine(description);
        Product product = new Product(productName, productDescription);
        if (database.addNewProduct(product)) {
            System.out.println("Seccessfully added");
        } else {
            System.out.println("Product has not been added");
        }
    }
}
