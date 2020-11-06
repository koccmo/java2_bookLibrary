package internet_store.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.domain.Product;

public class DeleteByIdService {

    private final ProductDatabase productDatabase;

    public DeleteByIdService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean execute(long id){
        for (int i = 0; i < productDatabase.getProductList().size(); i++){
            if (getCurrentProduct(i).getId() == id){
                productDatabase.deleteById(id);
                return true;
            }
        }
        return false;
    }

    private Product getCurrentProduct (int index){
        return productDatabase.getProductList().get(index);
    }
}
