package estore.service;

import estore.database.ProductDataBase;
import estore.domain.Product;

public class AddNewProductService {

    private ProductDataBase database;

    public AddNewProductService(ProductDataBase database) {
        this.database = database;
    }

    public boolean execute(String productName, String productDescription) {
        Product product = new Product(productName, productDescription);
        boolean successfullyAdded = database.addNewProduct(product);
        return successfullyAdded;
    }
}
