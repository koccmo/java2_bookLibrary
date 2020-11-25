package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.ChangeDescriptionRequest;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.product.ChangeDescriptionResponse;
import internet_store_1.database.product.ProductDatabase;

import java.util.List;

public class ChangeDescriptionService {

    private final ProductDatabase productDatabase;
    private final ChangeDescriptionRequestValidator changeDescriptionRequestValidator;

    public ChangeDescriptionService(ProductDatabase productDatabase, ChangeDescriptionRequestValidator changeDescriptionRequestValidator) {
        this.productDatabase = productDatabase;
        this.changeDescriptionRequestValidator = changeDescriptionRequestValidator;
    }

    public ChangeDescriptionResponse execute(ChangeDescriptionRequest changeDescriptionRequest){

        List<CoreError> errors = changeDescriptionRequestValidator.validate(changeDescriptionRequest);

        if (!errors.isEmpty()){
            return new ChangeDescriptionResponse(errors);
        }

        if (containsDatabaseSpecificId(changeDescriptionRequest.getId())){
            productDatabase.changeDescription(changeDescriptionRequest.getId(), changeDescriptionRequest.getDescription());
            return new ChangeDescriptionResponse(changeDescriptionRequest.getId());
        }else{
            errors.add(new CoreError("database", "Database doesn't contain product with id "
                    + changeDescriptionRequest.getId()));
            return new ChangeDescriptionResponse(errors);
        }
    }

    private boolean containsDatabaseSpecificId(long id){
        for (int i = 0; i < productDatabase.getProducts().size(); i++){
            if (id == productDatabase.getProducts().get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
