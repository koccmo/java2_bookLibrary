package internet_store.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

public class AddProductService {

    private final ProductDatabase productDatabase;

    public AddProductService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean addProduct(Product product){
        if (productDatabase.getProductList().contains(product)){
            return false;
        }else{
            productDatabase.addProduct(product);
            return true;
        }
    }

}
