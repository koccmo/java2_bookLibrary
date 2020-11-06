package internet_store.services.product;

import internet_store.database.product.ProductDatabase;

public class ChangeDescriptionService {

    private final ProductDatabase productDatabase;

    public ChangeDescriptionService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean execute(long id, String newDescription){
        if (containsDatabaseSpecificId(id)){
            productDatabase.changeDescription(id, newDescription);
            return true;
        }else{
            return false;
        }
    }

    private boolean containsDatabaseSpecificId(long id){
        for (int i = 0; i < productDatabase.getProductList().size(); i++){
            if (id == productDatabase.getProductList().get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
