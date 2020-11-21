package internet_store.core.services.product;

import internet_store.core.requests.customer.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.database.product.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchProductService {

    private final ProductDatabase productDatabase;
    private final SearchProductRequestValidator searchProductRequestValidator;

    public SearchProductService(ProductDatabase productDatabase, SearchProductRequestValidator searchProductRequestValidator) {
        this.productDatabase = productDatabase;
        this.searchProductRequestValidator = searchProductRequestValidator;
    }

    public SearchProductResponse execute (SearchProductRequest searchProductRequest){
        List<CoreError> errors = searchProductRequestValidator.validate(searchProductRequest);

        if (!errors.isEmpty()){
            return new SearchProductResponse(errors, new ArrayList<>());
        }

        if (isTitleAndDescriptionNotEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription())){
            return searchByTitleAndDescriptionIsProvided(searchProductRequest.getTitle(), searchProductRequest.getDescription());
        }

        if (isTitleFilled(searchProductRequest.getTitle())){
            return searchByTitleIsProvided(searchProductRequest.getTitle());
        }

        return searchByDescriptionIsProvided (searchProductRequest.getDescription());

    }

    private boolean isTitleAndDescriptionNotEmpty(String title, String description){
        return !title.isEmpty() && !description.isEmpty();
    }

    private SearchProductResponse searchByTitleAndDescriptionIsProvided(String title, String description){
        List <CoreError>errors = new ArrayList<>();
        if (productDatabase.findAllByTitleAndDescription(title, description).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain product with title: " +
                    title + " and description: " + description));
        }
        if (!errors.isEmpty()){
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        return new SearchProductResponse(productDatabase.findAllByTitleAndDescription(title, description));
    }

    private boolean isTitleFilled(String title){
        return !title.isEmpty();
    }

    private SearchProductResponse searchByTitleIsProvided(String title){
        List <CoreError>errors = new ArrayList<>();
        if (productDatabase.findAllByTitle(title).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products wits title: " +
                    title));
        }
        if (!errors.isEmpty()){
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        return new SearchProductResponse(productDatabase.findAllByTitle(title));
    }

    private SearchProductResponse searchByDescriptionIsProvided(String description){
        List <CoreError>errors = new ArrayList<>();
        if (productDatabase.findAllByDescription(description).isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain products wits description: " +
                    description));
        }
        if (!errors.isEmpty()){
            return new SearchProductResponse(errors, new ArrayList<>());
        }
        return new SearchProductResponse(productDatabase.findAllByDescription(description));
    }
}
