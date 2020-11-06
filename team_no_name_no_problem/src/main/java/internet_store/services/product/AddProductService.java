package internet_store.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

public class AddProductService {

    private final ProductDatabase productDatabase;

    public AddProductService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void addProduct(Product product){
        productDatabase.add(product);
    }

}
