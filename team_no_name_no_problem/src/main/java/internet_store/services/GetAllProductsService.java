package internet_store.services;

import internet_store.database.ProductDatabase;
import internet_store.domain.Product;

import java.util.List;

public class GetAllProductsService {

    private final ProductDatabase productDatabase;

    public GetAllProductsService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public List<Product> execute(){
        return productDatabase.getProductList();
    }
}
