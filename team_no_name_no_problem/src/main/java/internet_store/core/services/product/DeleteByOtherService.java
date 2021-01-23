package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.validators.DeleteByOtherRequestValidator;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByOtherService {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteByOtherRequestValidator deleteByOtherRequestValidator;

    public CoreResponse execute(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);
        if (!errors.isEmpty()) {
            return new DeleteByOtherResponse(errors);
        }
        return provideDeleteResultAccordingToRequest(deleteProductByOtherRequest);
    }

    private DeleteByOtherResponse provideDeleteResultAccordingToRequest(DeleteProductByOtherRequest
                                                                               deleteProductByOtherRequest) {

        if (isTitleAndDescriptionAndPriceNotEmptyForDelete(deleteProductByOtherRequest.getTitle(), deleteProductByOtherRequest.getDescription(),
                deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice())) {
            return deleteByTitleAndDescriptionAndPriceIsProvided(deleteProductByOtherRequest);
        }
        if (isTitleAndDescriptionFilledForDelete(deleteProductByOtherRequest.getTitle(),deleteProductByOtherRequest.getDescription())) {
            return deleteByTitleAndDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (isTitleFilledAndPriceRangeNotEmptyForDelete(deleteProductByOtherRequest.getTitle(),deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice())) {
            return deleteByTitleAndPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (isDescriptionFilledAndPriceRangeNotEmptyForDelete(deleteProductByOtherRequest.getDescription(),deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice())){
            return deleteByDescriptionAndPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (isTitleFilledToForDelete(deleteProductByOtherRequest.getTitle())) {
            return deleteByTitleIsProvidedForDelete(deleteProductByOtherRequest);
        }
        if (isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription())) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (isPriceRangeFilledForDelete(deleteProductByOtherRequest.getStartPrice(),deleteProductByOtherRequest.getEndPrice())) {
            return deleteByPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (!isTitleAndDescriptionAndPriceNotEmptyForDelete(deleteProductByOtherRequest.getTitle(), deleteProductByOtherRequest.getDescription(),
                deleteProductByOtherRequest.getStartPrice(), deleteProductByOtherRequest.getEndPrice())) {
            return deleteByTitleAndDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (!isTitleAndDescriptionFilledForDelete(deleteProductByOtherRequest.getTitle(),deleteProductByOtherRequest.getDescription())) {
            return deleteByTitleAndDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (!isTitleFilledAndPriceRangeNotEmptyForDelete(deleteProductByOtherRequest.getTitle(),deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice())) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }
        if (isDescriptionFilledAndPriceRangeNotEmptyForDelete(deleteProductByOtherRequest.getDescription(),deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice())){
            return deleteByDescriptionAndPriceRangeIsProvided(deleteProductByOtherRequest);
        }
        if (isTitleFilledToForDelete(deleteProductByOtherRequest.getTitle())) {
            return deleteByTitleIsProvidedForDelete(deleteProductByOtherRequest);
        }
        if (isDescriptionFilledForDelete(deleteProductByOtherRequest.getDescription())) {
            return deleteByDescriptionIsProvided(deleteProductByOtherRequest);
        }

        return deleteByTitleAndDescriptionAndPriceIsProvided(deleteProductByOtherRequest);
    }

    private boolean isTitleAndDescriptionAndPriceNotEmptyForDelete(String title, String description,
                                                                   Integer startPrice, Integer endPrice){
        return title != null && !title.isEmpty() && description != null && !description.isEmpty() &&
                startPrice != null && endPrice != null;
    }

    private boolean isTitleAndDescriptionFilledForDelete(String title, String description) {
        return title != null && !title.isEmpty() && description != null && !description.isEmpty();
    }

    private boolean isTitleFilledAndPriceRangeNotEmptyForDelete(String title, Integer startPrice, Integer endPrice) {
        return title != null && !title.isEmpty() && startPrice != null && endPrice != null;
    }

    private boolean isDescriptionFilledAndPriceRangeNotEmptyForDelete(String description, Integer startPrice, Integer endPrice) {
        return description != null && !description.isEmpty() && startPrice != null && endPrice != null;
    }

    private boolean isTitleFilledToForDelete(String title){
        return title != null && !title.isEmpty();
    }

    private boolean isDescriptionFilledForDelete(String description) {
        return description != null && !description.isEmpty();
    }

    private boolean isPriceRangeFilledForDelete(Integer startPrice, Integer endPrice) {
        return startPrice != null && endPrice != null;
    }

    private DeleteByOtherResponse deleteByTitleAndDescriptionAndPriceIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByTitleDescriptionAndPriceRange = productDatabase.deleteAllByTitleAndDescriptionAndPriceRange(deleteProductByOtherRequest.getTitle(),
                deleteProductByOtherRequest.getDescription(),deleteProductByOtherRequest.getStartPrice(),deleteProductByOtherRequest.getEndPrice());
        if (!resultOfDeleteByTitleDescriptionAndPriceRange){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain product with title: " +
                    deleteProductByOtherRequest.getTitle() + ", description: " + deleteProductByOtherRequest.getDescription() +
                    ", price range: from " + deleteProductByOtherRequest.getStartPrice() + " till " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByTitleDescriptionAndPriceRange);
    }

    private DeleteByOtherResponse deleteByTitleAndDescriptionIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByTitleDescription = productDatabase.deleteAllByTitleAndDescription(deleteProductByOtherRequest.getTitle(),
                deleteProductByOtherRequest.getDescription());
        if (!resultOfDeleteByTitleDescription){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain product with title: " +
                    deleteProductByOtherRequest.getTitle() + ", description: " + deleteProductByOtherRequest.getDescription()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByTitleDescription);
    }

    private DeleteByOtherResponse deleteByTitleAndPriceRangeIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByTitleAndPriceRange = productDatabase.deleteAllByTitleAndPriceRange(deleteProductByOtherRequest.getTitle(),
                deleteProductByOtherRequest.getStartPrice(),deleteProductByOtherRequest.getEndPrice());
        if (!resultOfDeleteByTitleAndPriceRange){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain product with title: " +
                    deleteProductByOtherRequest.getDescription() +
                    ", price range: from " + deleteProductByOtherRequest.getStartPrice() + " till " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByTitleAndPriceRange);
    }

    private DeleteByOtherResponse deleteByDescriptionAndPriceRangeIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByDescriptionAndPriceRange = productDatabase.deleteAllByDescriptionAndPriceRange(deleteProductByOtherRequest.getDescription(),
                deleteProductByOtherRequest.getStartPrice(),deleteProductByOtherRequest.getEndPrice());
        if (!resultOfDeleteByDescriptionAndPriceRange){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain product with description: " +
                    deleteProductByOtherRequest.getDescription() +
                    ", price range: from " + deleteProductByOtherRequest.getStartPrice() + " till " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByDescriptionAndPriceRange);
    }


    private DeleteByOtherResponse deleteByTitleIsProvidedForDelete(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByTitle = productDatabase.deleteAllByTitle(deleteProductByOtherRequest.getTitle());
        if (!resultOfDeleteByTitle){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain products with title: " +
                    deleteProductByOtherRequest.getTitle()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByTitle);
    }

    private DeleteByOtherResponse deleteByDescriptionIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest){
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByDescription = productDatabase.deleteAllByDescription(deleteProductByOtherRequest.getDescription());
        if (resultOfDeleteByDescription){
            errors.add(new CoreError("database", "Cannot delete. Database doesn't contain products with description: " +
                    deleteProductByOtherRequest.getDescription()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByDescription);
    }

    private DeleteByOtherResponse deleteByPriceRangeIsProvided(DeleteProductByOtherRequest deleteProductByOtherRequest) {
        List <CoreError>errors = new ArrayList<>();
        boolean resultOfDeleteByPriceRange = productDatabase.deleteAllByPriceRange(deleteProductByOtherRequest.getStartPrice(),
                deleteProductByOtherRequest.getEndPrice());
        if (resultOfDeleteByPriceRange) {
            errors.add (new CoreError("database","Cannot delete. Database doesn't contain products with price" +
                    " range starting from: " + deleteProductByOtherRequest.getStartPrice() +
                    " end ending with " + deleteProductByOtherRequest.getEndPrice()));
            return new DeleteByOtherResponse(errors);
        }
        return new DeleteByOtherResponse(resultOfDeleteByPriceRange);
    }
}
