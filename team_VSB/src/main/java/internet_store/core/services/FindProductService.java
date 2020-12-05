package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.domain.Product;
import internet_store.core.requests.FindProductRequest;
import internet_store.core.responses.CoreError;
import internet_store.core.responses.FindProductResponse;
import internet_store.core.services.validators.FindProductRequestValidator;

import java.util.List;

public class FindProductService {

    private final Database database;
    private final FindProductRequestValidator validator;
    private final OrderingProductService orderingProductService;
    private final PagingProductService pagingProductService;


    public FindProductService(Database database, FindProductRequestValidator validator,
                              OrderingProductService orderingProductService,
                              PagingProductService pagingProductService) {
        this.database = database;
        this.validator = validator;
        this.orderingProductService = orderingProductService;
        this.pagingProductService = pagingProductService;
    }

    public FindProductResponse execute(FindProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new FindProductResponse(errors, null);
        }

        List<Product> products = search(request);
        products = orderingProductService.order(products, request.getOrdering());
        products = pagingProductService.page(products, request.getPaging());
        return new FindProductResponse(null, products);
    }

    private List<Product> search(FindProductRequest request) {
        List<Product> products = null;
        if (request.isNameProvided() && !request.isDescriptionProvided()) {
            products = database.findByProductName(request.getProductName());
        }
        if (!request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByProductDescription(request.getProductDescription());
        }
        if (request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByProductNameAndDescription(request.getProductName(), request.getProductDescription());
        }
        return products;
    }
}
