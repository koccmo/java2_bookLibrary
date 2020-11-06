package internet_store.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

import java.util.Optional;

public class FindAnyByTitleService {

    private final ProductDatabase productDatabase;

    public FindAnyByTitleService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public Optional<Product>execute(String title){
        return productDatabase.findAnyByTitle(title);
    }
}
