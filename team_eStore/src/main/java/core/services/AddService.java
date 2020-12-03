package core.services;

import core.request.CoreRequest;
import core.responses.AddResponse;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.validators.iValidator;
import domain.Product;
import repository.iDatabase;

public class AddService implements iService{

    private iValidator validator;
    private iDatabase database;

    public AddService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /** Method to add product.
    * @param request CoreRequest
    * @return CoreResponse
    */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            Product product = new Product(request.getName(),
                                            request.getDescription(),
                                            request.getPrice());
            response = new AddResponse(database.addProduct(product));
        } else {
            response = new AddResponse(errors);
        }
        return response;
    }
}
