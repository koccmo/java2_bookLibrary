package internet_store.core.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

public class AddProductService {

    private final ProductDatabase productDatabase;

    public AddProductService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean execute(Product product){
        if (productDatabase.getProducts().contains(product)){
            return false;
        }else{
            productDatabase.add(product);
            return true;
        }
    }

}
