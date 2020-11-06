package internet_store.services;

import internet_store.database.ProductDatabase;
import internet_store.domain.Product;

public class AddProductServices {

    private final ProductDatabase productDatabase;

    public AddProductServices(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void addProduct(Product product){
        productDatabase.save(product);
    }


}
