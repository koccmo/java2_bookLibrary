package internet_store.core.services.product;

import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;

public class DeleteByIdService {

    private final ProductDatabase productDatabase;

    public DeleteByIdService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean execute(long id){
        for (int i = 0; i < productDatabase.getProducts().size(); i++){
            if (getCurrentProduct(i).getId() == id){
                productDatabase.deleteById(id);
                return true;
            }
        }
        return false;
    }

    private Product getCurrentProduct (int index){
        return productDatabase.getProducts().get(index);
    }
}
