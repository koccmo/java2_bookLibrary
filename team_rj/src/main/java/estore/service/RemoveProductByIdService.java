package estore.service;

import estore.database.ProductDataBase;

public class RemoveProductByIdService {

    private ProductDataBase database;

    public RemoveProductByIdService(ProductDataBase database) {
        this.database = database;
    }

    public int execute(int productToRemoveId) {
        int productsRemoved = database.removeProductById(Long.valueOf(productToRemoveId));
        return productsRemoved;
    }

}
