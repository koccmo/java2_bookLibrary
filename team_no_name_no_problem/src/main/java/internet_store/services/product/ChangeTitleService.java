package internet_store.services.product;

import internet_store.database.product.ProductDatabase;

public class ChangeTitleService {

    private final ProductDatabase productDatabase;

    public ChangeTitleService(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public boolean execute(long id, String newTitle){
        if (databaseContainsSpecificId(id)){
            productDatabase.changeTitle(id, newTitle);
            return true;
        }else{
            return false;
        }
    }

    private boolean databaseContainsSpecificId(long id){
        for (int i = 0; i < productDatabase.getProductList().size(); i++){
            if (productDatabase.getProductList().get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}
