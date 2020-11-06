package internet_store.services;

import internet_store.database.ProductDatabase;
import internet_store.domain.Product;

import java.util.List;

public class GetProductListService {

    private final ProductDatabase productDatabase;

    public GetProductListService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public List<Product> execute(){
        return productDatabase.getProductList();
    }
}
