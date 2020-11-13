package estore.service;

import estore.database.ProductDataBase;

public class RemoveProductByNameService {

    private ProductDataBase database;

    public RemoveProductByNameService(ProductDataBase database) {
        this.database = database;
    }

    public int execute(String productToRemoveName) {
        int productsRemoved = database.removeProductByName(productToRemoveName);
        return productsRemoved;
    }

}
