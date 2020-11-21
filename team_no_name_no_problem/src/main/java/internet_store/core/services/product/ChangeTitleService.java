package internet_store.core.services.product;

import internet_store.core.requests.product.ChangeTitleRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeTitleResponse;
import internet_store.database.product.ProductDatabase;

import java.util.List;

public class ChangeTitleService {

    private final ProductDatabase productDatabase;
    private final ChangeTitleRequestValidator changeTitleRequestValidator;

    public ChangeTitleService(ProductDatabase productDatabase, ChangeTitleRequestValidator changeTitleRequestValidator) {
        this.productDatabase = productDatabase;
        this.changeTitleRequestValidator = changeTitleRequestValidator;
    }

    public ChangeTitleResponse execute(ChangeTitleRequest changeTitleRequest){

        List<CoreError> errors = changeTitleRequestValidator.validate(changeTitleRequest);

        if (!errors.isEmpty()){
            return new ChangeTitleResponse(errors);
        }

        if (databaseContainsSpecificId(changeTitleRequest.getId())){
            productDatabase.changeTitle(changeTitleRequest.getId(), changeTitleRequest.getTitle());
            return new ChangeTitleResponse(changeTitleRequest.getId());
        }else{
            errors.add(new CoreError("database", "Database doesn't contain id "
                    + changeTitleRequest.getId()));
            return new ChangeTitleResponse(errors);
        }
    }

    private boolean databaseContainsSpecificId(long id){
        for (int i = 0; i < productDatabase.getProducts().size(); i++){
            if (productDatabase.getProducts().get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}
