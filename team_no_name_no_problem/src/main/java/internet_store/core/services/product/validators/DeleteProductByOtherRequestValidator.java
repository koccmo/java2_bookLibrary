package internet_store.core.services.product.validators;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteProductByOtherRequestValidator {

    public List<CoreError> validate (DeleteProductByOtherRequest deleteProductByOtherRequest){

        List <CoreError> errors = new ArrayList<>();

        if (isTitleAndDescriptionEmptyAndPriceRangeMissingForDelete(deleteProductByOtherRequest.getTitle(), deleteProductByOtherRequest.getDescription(),
                deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice())){
            errors.add(new CoreError("search", "Not valid input for search"));
            return errors;
        }

        if (isNotValidInputForPriceDelete(deleteProductByOtherRequest)) {
            errors.add(new CoreError("price", "Not valid input for prices"));
        }

        return errors;
    }

    private boolean isTitleAndDescriptionEmptyAndPriceRangeMissingForDelete(String title, String description,
                                                                            Integer startPrice, Integer endPrice){
        return (title == null || title.isEmpty()) && (description == null || description.isEmpty()) &&
                startPrice == null && endPrice == null;
    }

    private boolean isNotValidInputForPriceDelete(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        if (deleteProductByOtherRequest.getEndPrice() != null && deleteProductByOtherRequest.getStartPrice() != null) {
            return pricesNegative(deleteProductByOtherRequest) || !fieldsFilledCorrect(deleteProductByOtherRequest);
        } else
            return !fieldsFilledCorrect(deleteProductByOtherRequest);
    }

    private boolean pricesNegative(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        return deleteProductByOtherRequest.getStartPrice() < 0
                || deleteProductByOtherRequest.getEndPrice() < 0;
    }

    private boolean fieldsFilledCorrect(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        return (deleteProductByOtherRequest.getStartPrice() != null && deleteProductByOtherRequest.getEndPrice() != null)
                || (deleteProductByOtherRequest.getStartPrice() == null && deleteProductByOtherRequest.getEndPrice() == null);
    }

}
