package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.responses.SearchResponse;
import core.validators.iValidator;
import domain.Product;
import repository.iDatabase;

import java.util.List;


public class SearchService implements iService{

    private iValidator validator;
    private iDatabase productDataBase;

    public SearchService(final iDatabase productDataBase, final iValidator validator) {
        this.productDataBase = productDataBase;
        this.validator = validator;
    }

    /**
     * Method to Search products by name.
     * param CoreRequest
     * return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        List<Product> products;
        Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            products = productDataBase.findByNameAndPrice(request.getName(), request.getPrice());
            response = new SearchResponse(products);
        } else {
            response = new SearchResponse(errors);
        }
        return response;
    }
}
